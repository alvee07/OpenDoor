/**
 * This file is taken from a web page. Nothing is modified in this file.
 *
 * <p>Code is taken from -
 *
 * <p>https://pepipost.com/tutorials/send-email-in-android-using-javamail-api/
 *
 * <p>Document was accessed on 2020-10-01
 *
 * <p>Collected by Alvee Hassan Akash
 */
package com.example.opendoorapp.Sending_GMail_Files;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataSource;

public class ByteArrayDataSource implements DataSource {
  private byte[] data;
  private String type;

  public ByteArrayDataSource(byte[] data, String type) {
    super();
    this.data = data;
    this.type = type;
  }

  public ByteArrayDataSource(byte[] data) {
    super();
    this.data = data;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getContentType() {
    if (type == null) return "application/octet-stream";
    else return type;
  }

  public InputStream getInputStream() throws IOException {
    return new ByteArrayInputStream(data);
  }

  public String getName() {
    return "ByteArrayDataSource";
  }

  public OutputStream getOutputStream() throws IOException {
    throw new IOException("Not Supported");
  }
}
