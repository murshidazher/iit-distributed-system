package ds.tutorials.name.service.client;

import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream; import java.net.HttpURLConnection; import java.net.URL;
import java.nio.charset.StandardCharsets; import java.util.Base64;
public class EtcdClient {
  private String etcdAddress;

  public EtcdClient(String etcdAddress) throws IOException {
    this.etcdAddress = etcdAddress;
  }

  public void put(String key, String value) throws IOException {
    System.out.println("Putting Key=" + key + ", Value=" + value);
    String putUrl = etcdAddress + "/v3/kv/put";
    String serverResponse = callEtcd(putUrl, buildPutRequestPayload(key, value));
    System.out.println(serverResponse);
  }

  public String get(String key) throws IOException {
    System.out.println("Getting value for Key=" + key);
    String getUrl = etcdAddress + "/v3/kv/range"; String serverResponse = callEtcd(getUrl,
      buildGetRequestPayload(key));
    return serverResponse;
  }

  private String callEtcd(String url, String payload) throws IOException {
    URL etcdUrl = new URL(url);
    HttpURLConnection connection = (HttpURLConnection) etcdUrl.openConnection();
    connection.setRequestMethod("POST"); connection.setDoOutput(true); connection.setRequestProperty("Content-Type",
      "application/json; charset=UTF-8"); connection.connect();
    OutputStream outputStream = connection.getOutputStream();
    outputStream.write(payload.getBytes(StandardCharsets.UTF_8));
    InputStream inputStream = connection.getInputStream();
    String serverResponse = readResponse(inputStream);
    inputStream.close(); outputStream.close(); connection.disconnect(); return serverResponse;
  }

  private String readResponse(InputStream inputStream) throws IOException {
    StringBuilder builder = new StringBuilder(); int character = inputStream.read();
    while (character != -1) {
      builder.append((char) character);
      character = inputStream.read(); }
    return builder.toString();
  }

  private String buildPutRequestPayload(String key,
                                        String value) {
    String keyEncoded = Base64.getEncoder().encodeToString(key.getBytes());
    String valueEncoded = Base64.getEncoder().encodeToString(value.getBytes());
    JSONObject putRequest = new JSONObject(); putRequest.put("key", keyEncoded); putRequest.put("value", valueEncoded); return putRequest.toString();
  }

  private String buildGetRequestPayload(String key) { String keyEncoded =
    Base64.getEncoder().encodeToString(key.getBytes()); JSONObject putRequest = new JSONObject(); putRequest.put("key", keyEncoded);

    return putRequest.toString();
  }
}
