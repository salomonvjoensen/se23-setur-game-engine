package com.setur.se23.engine.render.common;

// note: this could be extended to take a byte[] array of pixel data instead of a path
public record Texture2D(byte[] image, int width, int height) {
}
