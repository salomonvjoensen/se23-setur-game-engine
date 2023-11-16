package com.setur.se23.engine.render;

import java.util.ArrayList;

import com.setur.se23.engine.render.common.Texture2D;
import com.setur.se23.engine.render.common.ViewPort;

public class Renderer {
    private static Renderer instance;
    private final RenderPipelineInterface _pipeline;

    // Private constructor to prevent instantiation from other classes
    private Renderer(RenderPipelineInterface pipeline) {
        _pipeline = pipeline;
    }

    /**
     * Instantiates the renderer with the given pipeline.
     *
     * @param pipeline The pipeline to use for rendering.
     * @return The singleton instance of the renderer.
     */
    public static Renderer Instantiate(RenderPipelineInterface pipeline) {
        synchronized (Renderer.class) {
            if (instance == null) {
                instance = new Renderer(pipeline);
            }
        }
        return instance;
    }

    public static void Destroy() {
        instance = null;
    }

    /**
     * Gets the singleton instance of the renderer.
     *
     * @return The singleton instance of the renderer.
     */
    public static Renderer getInstance() {
        return instance;
    }

    public void render(ArrayList<BufferItem> bufferItems) {

        for (BufferItem item : bufferItems) {
            _pipeline.render(item.material(), item.x(), item.y());
        }
    }

    /**
     * Allocates a texture into the render pipeline, to process it for rendering.
     *
     * @param texture The texture to allocate
     */
    public void allocateTexture(Texture2D texture) {
        _pipeline.allocateTexture(texture);
    }

    /**
     * Initializes the renderer with the viewport.
     *
     * @param viewport The viewport to initialize the renderer with.
     */
    public void initialize(ViewPort viewport) {
        _pipeline.initialize(viewport);
    }

    /**
     * Renders the current frame.
     */
    public void swapBuffers() {
        _pipeline.swapBuffers();
    }
}