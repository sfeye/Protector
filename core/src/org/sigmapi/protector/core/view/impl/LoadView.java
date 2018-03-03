/*
 * Copyright (c) 2018, SigmaPi-eHacks2018 Team
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.sigmapi.protector.core.view.impl;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader.FreeTypeFontLoaderParameter;

import org.sigmapi.protector.core.Protector;
import org.sigmapi.protector.core.background.Background;
import org.sigmapi.protector.core.font.Font;
import org.sigmapi.protector.core.input.InputEvent;
import org.sigmapi.protector.core.skin.AsteroidSkin;
import org.sigmapi.protector.core.skin.LaserSkin;
import org.sigmapi.protector.core.skin.VesselSkin;
import org.sigmapi.protector.core.Statics;
import org.sigmapi.protector.core.view.AbstractView;

import java.util.Deque;

/**
 * Created by Kyle Fricilone on Mar 02, 2018.
 */
public class LoadView extends AbstractView
{

	private final AssetManager assets;

	public LoadView(Protector protector)
	{
		super(protector);
		this.assets = protector.getAssets();

		for (Background bg : Background.values())
		{
			assets.load(bg.getPath(), Texture.class);
		}

		for (AsteroidSkin skin : AsteroidSkin.values())
		{
			assets.load(skin.getPath(), Texture.class);
		}

		for (LaserSkin skin : LaserSkin.values())
		{
			assets.load(skin.getPath(), Texture.class);
		}

		for (VesselSkin skin : VesselSkin.values())
		{
			assets.load(skin.getPath(), Texture.class);
		}

		for (Font font : Font.values())
		{
			FreeTypeFontLoaderParameter param = new FreeTypeFontLoaderParameter();
			param.fontFileName = font.getPath();
			param.fontParameters.size = (int) (Statics.WIDTH / font.getRatio());
			assets.load(font.getPath(), BitmapFont.class, param);
		}
	}

	@Override
	public void accept(Deque<InputEvent> events)
	{

	}

	@Override
	public void update(float delta)
	{
		assets.update();

		if (assets.getProgress() == 1.0f)
		{
			protector.getState().setLoaded(true);
			protector.getViews().pop();
			protector.getViews().push(new MenuView(protector));
		}
	}

	@Override
	public void render(SpriteBatch batch)
	{

	}

	@Override
	public void dispose()
	{

	}
}
