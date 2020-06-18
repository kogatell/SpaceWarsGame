package com.mygdx.game.game;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Button {
    private final float textY;
    private final float textX;
    public Texture buttonText;
    String text;
    BitmapFont font;
    GlyphLayout glyphLayout;
    Rectangle bounds;
    Vector2 position;
    Vector2 dimension;

    public Button(String text, float x, float y, float width, float height) {
        this.text = text;
        font = new BitmapFont();
        font.getData().setScale(2);
        this.dimension = new Vector2(width, height);
        this.position = new Vector2(x, y);
        glyphLayout = new GlyphLayout(font, text);
        buttonText = createButtonTexture();
        textX = position.x + (dimension.x - glyphLayout.width) / 2;
        textY = position.y + dimension.y - (dimension.y - glyphLayout.height) / 2;
        bounds = new Rectangle(position.x, position.y, dimension.x, dimension.y);
    }

    public void render(Batch batch) {
        batch.draw(buttonText, position.x, position.y, dimension.x, dimension.y);
    }

    private Texture createButtonTexture() {
        Pixmap pm = new Pixmap(10, 10, Pixmap.Format.RGBA8888);
        pm.setColor(0, 0, 1, 1);
        pm.drawRectangle(0, 0, 10, 10);
        pm.setColor(0, 0, 1, 1);
        pm.fillRectangle(1, 1, 8, 8);
        return new Texture(pm);
    }

    public boolean contains(float x, float y) {
        return bounds.contains(x, y);
    }

    public boolean click(float x, float y) {
        if (this.contains(x, y)) {
            return true;
        }
        return false;
    }
}
