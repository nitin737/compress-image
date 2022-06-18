package com.tools.imagetype;

public enum Image {
  BMP("bmp"),
  GIF("gif"),
  JPEG("jpeg"),
  JPG("jpg"),
  PNG("png"),
  TIFF("tiff"),
  WBMP("wbmp");
  private String ext;

  Image(String extension) {
    this.ext = extension;
  }

  @Override
  public String toString() {
    return ext;
  }

  public static boolean contains(String ext) {
    for (Image value : Image.values()) if (value.toString().equals(ext)) return true;
    return false;
  }
}
