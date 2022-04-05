/*******************************************************************************
 * Copyright 2014 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.siegetd.game.models.ecs.components;

import com.badlogic.ashley.core.Component;
import com.siegetd.game.models.map.tile.MovableTile;

import java.util.LinkedList;

public class MovementComponent implements Component {
	public float velocityX;
	public float velocityY;
	private final int MOVE_SPEED = 256;

	private int index;
	private float nextX, nextY;
	private LinkedList<MovableTile> path;

	public MovementComponent (float velocityX, float velocityY, LinkedList<MovableTile> moveableTiles) {
		this.velocityX = velocityX;
		this.velocityY = velocityY;

		this.index = 0;
		this.path = moveableTiles;
		getNextPos();
	}

	public void updateMovement(float curX, float curY){
		float x_dis = nextX - curX;
		float y_dis = nextY - curY;

		if (x_dis == 0 && y_dis == 0 ){
			index++;
			getNextPos();
		}
		else{
			this.velocityX = MOVE_SPEED*Integer.signum((int)x_dis);
			this.velocityY = MOVE_SPEED*Integer.signum((int)y_dis);
		}
	}

	private void getNextPos(){
		if(index>=path.size()){
			this.velocityX = 0;
			this.velocityY = 0;
			return;
		}
		nextX = path.get(index).getX();
		nextY = path.get(index).getY();

		System.out.println(nextX + " || " + nextY);
	}
}
