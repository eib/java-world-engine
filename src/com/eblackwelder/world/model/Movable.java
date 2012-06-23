/**
 * 
 */
package com.eblackwelder.world.model;

import com.eblackwelder.math.Velocity;

/**
 * @author Ethan
 *
 */
public interface Movable extends Positionable {

	public Velocity getVelocity();

	public void setVelocity(Velocity velocity);
}
