package org.maxwu.jrefresh.HttpApi;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.net.ssl.*;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.maxwu.jrefresh.ColorPrint;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by maxwu on 2/2/17.
 * This class is introduced to test WebAPI samples with JUnit

 Here is the sample logs in Bash shell CLI with iTerm2.

 JSON, XML and CSV format are supported.
 ⓑ maxwu  curl https://freegeoip.net/json/
 {"ip":"121.73.44.198","country_code":"NZ","country_name":"New Zealand","region_code":"WGN","region_name":"Wellington","city":"Wellington","zip_code":"6021","time_zone":"Pacific/Auckland","latitude":-41.3053,"longitude":174.779,"metro_code":0}

 ⓑ maxwu  curl https://api.ipify.org
 121.73.44.198
 ⓑ maxwu 

 */
public class SourceIpApiTest {
    private OkHttpClient client = null;

    @Before
    public void setUp() throws Exception {
        //client = new OkHttpClient();
        OkHttpClient.Builder clientBuilder = new OkHttpClient().newBuilder();
        final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                X509Certificate[] cArrr = new X509Certificate[0];
                return cArrr;
            }

            @Override
            public void checkServerTrusted(final X509Certificate[] chain,
                                           final String authType) throws CertificateException {
            }

            @Override
            public void checkClientTrusted(final X509Certificate[] chain,
                                           final String authType) throws CertificateException {
            }
        }};

        SSLContext sslContext = SSLContext.getInstance("SSL");

        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        clientBuilder.sslSocketFactory(sslContext.getSocketFactory());

        HostnameVerifier hostnameVerifier = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                ColorPrint.println_blue("Trusts Host :" + hostname);
                return true;
            }
        };
        clientBuilder.hostnameVerifier( hostnameVerifier);
        client = clientBuilder.build();
    }

    public boolean checkIpv4(String ip) throws Exception{
        return ip.matches("(\\d+\\.){3}\\d+");
    }

    @Test
    public void httpFreegeoipJsonTest() throws Exception {
        Request request = new Request.Builder()
                .url(Constants.Freegeoio_Url_Json)
                .build();
        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new IOException("Service Error" + response);
        }

        Assert.assertEquals(200, response.code());
        Assert.assertEquals("application/json", response.body().contentType().toString());

        JSONObject jsonResp = new JSONObject(response.body().string());
        String ip = jsonResp.getString("ip");
        Assert.assertTrue(checkIpv4(ip));

        //Debug part:
        Headers respHeaders = response.headers();
        respHeaders.names().forEach(n -> ColorPrint.println_green("header name:" + n + ", value:" + respHeaders.get(n)));
        ColorPrint.println_blue(jsonResp.toString(2));

    }

    @Test
    public void httpIpifyTest() throws Exception {
        Request request = new Request.Builder()
                .url(Constants.Ipify_Url)
                .build();
        Response response = client.newCall(request).execute();

        Assert.assertEquals(200, response.code());
        Assert.assertEquals("text/plain", response.body().contentType().toString());
        String strResp = response.body().string().trim();
        Assert.assertTrue(checkIpv4(strResp));

        Headers respHeaders = response.headers();
        respHeaders.names().forEach(n -> ColorPrint.println_green("header name:" + n + ", value:" + respHeaders.get(n)));
        ColorPrint.println_blue(strResp);
    }

}
