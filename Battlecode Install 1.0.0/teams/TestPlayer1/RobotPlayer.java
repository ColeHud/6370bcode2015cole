/**
 * @author Cole Hudson
 * @version 1/5/2015
 */
//testing for the map oneTower
package TestPlayer1;

import battlecode.common.*;

import java.util.*;

public class RobotPlayer 
{
	public static void run(RobotController rc)
	{
		while(true)
		{
			//HQ code
			if(rc.getType()==RobotType.HQ)
			{
				hqCode(rc);
				
			}
			else if(rc.getType()==RobotType.BEAVER)
			{
				beaverCode(rc);
			}
		}
	}
	
	//beaver code
	private static void beaverCode(RobotController rc) 
	{
		Direction e = null;
		if(Clock.getRoundNum()<= 500)
		{
			e = rc.getLocation().directionTo(rc.getLocation().add(Direction.EAST, 20));
		}
		else
		{
			e = rc.getLocation().directionTo(rc.senseEnemyHQLocation());
		}
	
		
		if(rc.canAttackLocation(rc.getLocation().add(Direction.SOUTH))&&rc.isCoreReady()&&rc.isWeaponReady())
		{
			try 
			{
				rc.attackLocation(rc.getLocation().add(Direction.SOUTH));
			} 
			catch (GameActionException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(rc.isCoreReady()&&rc.canMove(e))
		{
			try
			{
				rc.move(e);
			}
			catch(GameActionException e1)
			{
				e1.printStackTrace();
			}
			
		}
		rc.yield();
	}

	//hq
	private static void hqCode(RobotController rc)
	{
		Direction d = Direction.SOUTH;
		MapLocation m = rc.getLocation().add(d);
		
		
			if(rc.canSpawn(d, RobotType.BEAVER)&&rc.isCoreReady())
			{
				
				try 
				{
					rc.spawn(d, RobotType.BEAVER);
				} 
				catch (GameActionException e) 
				{
					System.out.println("Error");
				}
			}
		
		
		rc.yield();
	}
	
}
