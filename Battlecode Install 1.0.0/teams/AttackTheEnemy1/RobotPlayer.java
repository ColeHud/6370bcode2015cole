package AttackTheEnemy1;
import java.util.Random;

import battlecode.common.*;
/**
 * 
 * @author Cole
 * @version 1/6/2015
 */

public class RobotPlayer 
{
	public static void run(RobotController rc)
	{
		while(true)
		{
			//hq
			if(rc.getType()==RobotType.HQ)
			{
				hqCode(rc);
			}
			//beaver
			else if(rc.getType()==RobotType.BEAVER)
			{
				beaverCode(rc);
			}
			
			rc.yield();
		}
	}
	
	
	
	//hq code
	private static void hqCode(RobotController rc)
	{
		spawnStuff(rc);	
	}
	
	
	
	//spawning code
	private static void spawnStuff(RobotController rc)
	{
		if(rc.isCoreReady() && rc.canSpawn(Direction.NORTH, RobotType.BEAVER))
		{
			try 
			{
				rc.spawn(Direction.NORTH, RobotType.BEAVER);
			} 
			catch (GameActionException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	//beaver code
	private static void beaverCode(RobotController rc)
	{
		Direction[] directions = {Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};
		
		Direction directionToMove = directions[(int)Math.random()*8];
		
		try 
		{
			//nearestEnemy
			rc.move(rc.getLocation().directionTo(findClosestEnemy(rc)));
			
			//randomDir
			//rc.move(directionToMove);
		} 
		catch (GameActionException e) 
		{
			e.printStackTrace();
		}
	}
	
	//find nearest enemy
	private static MapLocation findClosestEnemy(RobotController rc)
	{
		//find direction to nearest enemy
		Team me = rc.getTeam();
		Team opponent = me.opponent();
		Direction enemy = null;

		int closestEnemyDistance = Integer.MAX_VALUE;
		MapLocation closestEnemyRobotLocation = null;

		for(RobotInfo info :rc.senseNearbyRobots(10000, opponent))
		{
			if(rc.getLocation().distanceSquaredTo(info.location) < closestEnemyDistance)
			{
				closestEnemyRobotLocation = info.location;
			}
		}
		
		return closestEnemyRobotLocation;
	}
	
	//simpleMoveTo
	private static void simpleMove(RobotController rc)
	{
		
	}
}
