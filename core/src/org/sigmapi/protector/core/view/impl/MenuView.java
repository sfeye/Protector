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

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import org.sigmapi.protector.core.Protector;
import org.sigmapi.protector.core.Statics;
import org.sigmapi.protector.core.background.Background;
import org.sigmapi.protector.core.input.InputEvent;
import org.sigmapi.protector.core.input.impl.TouchDownEvent;
import org.sigmapi.protector.core.view.AbstractView;

import java.util.Deque;

/**
 * Created by Kyle Fricilone on Mar 02, 2018.
 */
public class MenuView extends AbstractView
{
	private final Texture bg0;
	private final Texture bg1;
	private final Texture logo;

	private float y0;
	private float y1;

	public MenuView(Protector protector)
	{
		super(protector);

		bg0 = protector.getAssets().get(Background.BG0.getPath(), Texture.class);
		bg1 = protector.getAssets().get(Background.BG1.getPath(), Texture.class);
		logo = protector.getAssets().get(Statics.LOGO, Texture.class);

		y0 = 0;
		y1 = Statics.HEIGHT;
	}

	@Override
	public void accept(Deque<InputEvent> events)
	{
		for (InputEvent event : events)
		{
			if (event instanceof TouchDownEvent)
			{
				protector.getViews().pop();
				protector.getViews().push(new GameView(protector));
			}
		}
	}

	@Override
	public void update(float delta)
	{
		y0 += Statics.BG_VEL * delta;
		y1 += Statics.BG_VEL * delta;

		if (y0 <= -Statics.HEIGHT)
		{
			y0 = Statics.HEIGHT;
		}

		if (y1 <= -Statics.HEIGHT)
		{
			y1 = Statics.HEIGHT;
		}
	}

	@Override
	public void render(SpriteBatch batch)
	{
		batch.draw(bg0, 0, y0, Statics.WIDTH, Statics.HEIGHT + 1);
		batch.draw(bg1, 0, y1, Statics.WIDTH, Statics.HEIGHT + 1);
		batch.draw(logo, 0, 0, Statics.WIDTH, Statics.HEIGHT);
	}

	@Override
	public void dispose()
	{
	}
}
