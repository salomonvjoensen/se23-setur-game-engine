package com.setur.se23.dependency.render.canvas;

import com.setur.se23.dependency.render.GUI.GUI_Item;
import com.setur.se23.engine.render.BufferItem;
import com.setur.se23.engine.render.RenderPipelineInterface;
import com.setur.se23.engine.render.common.Texture2D;
import com.setur.se23.engine.render.common.ViewPort;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CanvasRenderer implements RenderPipelineInterface {

    private final Stage _stage;

    private final List<BufferItem> _buffer = new ArrayList<>();
    private static final List<GUI_Item> _GUIbuffer = new ArrayList<>();

    private final HashMap<String, Image> _textureMap = new HashMap<>();

    private Canvas _canvas;
    private static AnchorPane anchorPane;

    public CanvasRenderer(Stage stage) {
        _stage = stage;
        
    }

    @Override
    public void initialize(ViewPort viewport) throws IllegalArgumentException {

        double width = viewport.width();
        double height = viewport.height();

        if (width < 0.0) {
            throw new IllegalArgumentException("Width must be larger than 0. Received: " + width);
        }
        if (height < 0.0) {
            throw new IllegalArgumentException("Height must be larger than 0. Received: " + height);
        }

        _canvas = new Canvas(width, height);
        anchorPane = new AnchorPane();

        var group = new Group(_canvas, anchorPane);
        var scene = new Scene(group, 320, 240);
        _stage.setScene(scene);
        _stage.show();
    }

    @Override
    public void allocateTexture(Texture2D texture) {
        _textureMap.put(texture.path(), new Image(texture.path()));
    }

    @Override
    public void swapBuffers() {
        var context = _canvas.getGraphicsContext2D();

        // Clears the entire screen
        context.clearRect(0, 0, _canvas.getHeight(), _canvas.getWidth());

        // iterates over all the requested render items, and pushes them onto the canvas
        for (var item : _buffer) {
            var texture = item.texture();

            Image img = _textureMap.get(texture.path());

            context.save();

            double pivotX = item.x() + (texture.width() * item.scaleX()) / 2;
            double pivotY = item.y() + (texture.height() * item.scaleY()) / 2;

            rotate(context, item.angle(), pivotX, pivotY);

            double width = texture.width() * item.scaleX();
            double height = texture.height() * item.scaleY();

            context.drawImage(img, item.x(), item.y(), width, height);

            context.restore();
        }

        // clears the buffer to make it ready for the next render pass.
        _buffer.clear();
        _GUIbuffer.clear();
    }

    private void rotate(GraphicsContext context, double angle, double pivotX, double pivotY) {
        Rotate r = new Rotate(angle, pivotX, pivotY);
        context.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    @Override
    public void render(Texture2D texture, double x, double y, double angle, double scaleX, double scaleY) {
        _buffer.add(new BufferItem(texture, x, y, angle, scaleX, scaleY));
    }

    /**
     * Adds GUI_Item to GUIbuffer.
     */
    public static void addToGUI(GUI_Item GUI_Item) {
        _GUIbuffer.add(GUI_Item);
    }

    /**
     * Removes old GUI and loads GUIbuffer.
     */
    public static void loadGUI() {
        anchorPane.getChildren().clear();

        for (GUI_Item item : _GUIbuffer) {

            AnchorPane.setLeftAnchor(item.node(), item.x());
            AnchorPane.setTopAnchor(item.node(), item.y());

            anchorPane.getChildren().add(item.node());
        }
        
    }
}
