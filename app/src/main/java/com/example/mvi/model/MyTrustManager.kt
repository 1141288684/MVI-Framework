package com.example.mvi.model

//import java.security.cert.X509Certificate
import android.annotation.SuppressLint
import android.content.Context
import com.example.mvi.R
import okhttp3.OkHttpClient
import java.io.IOException
import java.io.InputStream
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
        const val PUB_KEY:String="MIICJzCCAc2gAwIBAgIQAOaCcCwhdaRnNGkn56WOazAKBggqhkjOPQQDAjAYMRYw\n" +
                "FAYDVQQDDA0xOTIuMTY4LjEuMTE5MB4XDTIyMDcyOTA2NTYyMFoXDTIzMDcyOTA2\n" +
                "NTYyMFowGDEWMBQGA1UEAwwNMTkyLjE2OC4xLjExOTBZMBMGByqGSM49AgEGCCqG\n" +
                "SM49AwEHA0IABMLr/+jf9CheUbkGej8CnZFFKFtGBjgrVWQ/QRhluMr1A+bKQx8L\n" +
                "hSz5AuSu30IsUTy+4bVii4dYMI91zVnTVvyjgfgwgfUwVQYDVR0RBE4wTIIMbS5r\n" +
                "b2FkZXIudG9wgg53d3cua29hZGVyLnRvcIIQY2xvdWQua29hZGVyLnRvcIIObmFz\n" +
                "LmtvYWRlci50b3CHBH8AAAGHBMCoAXcwHQYDVR0OBBYEFO9c/uRaMeDWqI3/KvUO\n" +
                "dghTrKPPMA4GA1UdDwEB/wQEAwIBhjAPBgNVHRMBAf8EBTADAQH/MDsGA1UdJQQ0\n" +
                "MDIGCCsGAQUFBwMCBggrBgEFBQcDAQYIKwYBBQUHAwMGCCsGAQUFBwMEBggrBgEF\n" +
                "BQcDCDAfBgNVHSMEGDAWgBTvXP7kWjHg1qiN/yr1DnYIU6yjzzAKBggqhkjOPQQD\n" +
                "AgNIADBFAiAAjPaPgX1spk0Aizn8k/ZWABGLu97UArZe7MlJgrqBqAIhAPHUoqJx\n" +
                "tOWd9EwKI0LvBT4rhp3MQSPc4Cz1d/iv2A7F"

        fun onHttpCertificate(context: Context,builder: OkHttpClient.Builder){
            getSSLSocketFactoryForOneWay(context.resources.openRawResource(R.raw.media))?.let {
                builder.sslSocketFactory(it,MyTrustManager()).hostnameVerifier(HostnameVerifier { hostname, _ -> return@HostnameVerifier hostname==GlobalConfig.domain })
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