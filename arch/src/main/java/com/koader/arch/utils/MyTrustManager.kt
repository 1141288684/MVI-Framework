package com.koader.arch.utils

//import java.security.cert.X509Certificate
import android.annotation.SuppressLint
import android.content.Context
import okhttp3.OkHttpClient
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.math.BigInteger
import java.security.KeyStore
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import java.security.interfaces.RSAPublicKey
import java.util.*
import javax.net.ssl.*


@SuppressLint("CustomX509TrustManager")
class MyTrustManager:X509TrustManager {

    companion object{
        private lateinit var PUB_KEY:String

        fun onHttpCertificate(ins:InputStream,builder: OkHttpClient.Builder,config: GlobalConfig.BaseConfig){
            PUB_KEY=config.pub_key!!
            getSSLSocketFactoryForOneWay(ins)?.let {
                builder.sslSocketFactory(it,
                    MyTrustManager()
                ).hostnameVerifier(HostnameVerifier { hostname, _ -> return@HostnameVerifier hostname== config.domain })
            }
        }
        /**
         * 单项认证
         */
        private fun getSSLSocketFactoryForOneWay(vararg certificates: InputStream?): SSLSocketFactory? {
            try {
                val certificateFactory: CertificateFactory =
                    CertificateFactory.getInstance("X.509")
                val keyStore = KeyStore.getInstance(KeyStore.getDefaultType())
                keyStore.load(null)
                for ((index, certificate) in certificates.withIndex()) {
                    val certificateAlias = index.toString()
                    keyStore.setCertificateEntry(
                        certificateAlias,
                        certificateFactory.generateCertificate(certificate)
                    )
                    try {
                        certificate?.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
                val sslContext = SSLContext.getInstance("TLS")
                val trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
                trustManagerFactory.init(keyStore)
                sslContext.init(null, trustManagerFactory.trustManagers, SecureRandom())
                return sslContext.socketFactory
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
            return null
        }
    }

    override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {
        if (chain == null) {
            throw IllegalArgumentException("checkServerTrusted:x509Certificate array isnull");
        }
        if (chain.isEmpty()) {
            throw IllegalArgumentException("checkServerTrusted: X509Certificate is empty");
        }
    }

    override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
        if (chain == null) {
            throw IllegalArgumentException("checkServerTrusted:x509Certificate array isnull");
        }

        if (chain.isEmpty()) {
            throw IllegalArgumentException("checkServerTrusted: X509Certificate is empty");
        }

        if (!(null != authType && authType.uppercase(Locale.ROOT) == "RSA")) {
            throw CertificateException("checkServerTrusted: AuthType is not RSA");
        }


        try {
            val tmf = TrustManagerFactory.getInstance("X509")
            tmf.init(KeyStore.getInstance(KeyStore.getDefaultType()))
            for (trustManager in tmf.trustManagers) {
                (trustManager as X509TrustManager).checkServerTrusted(chain, authType)
            }
        } catch (e:Exception) {
            throw  CertificateException(e)
        }


        val pubKey = (chain[0] as RSAPublicKey)

        val encoded = BigInteger(1 , pubKey.encoded).toString(16);

        val expected = PUB_KEY.lowercase()==encoded.lowercase()

        if (!expected) {
            throw CertificateException("checkServerTrusted: Expected public key: "
                    + PUB_KEY + ", got public key:" + encoded)
        }
    }

    override fun getAcceptedIssuers(): Array<X509Certificate?> {
        return arrayOfNulls(0)
    }


}