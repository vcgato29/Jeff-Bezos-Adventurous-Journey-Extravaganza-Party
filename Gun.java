public class Gun
{
public int damage;
public int ammo;
public int clip;
public int spot; //in loadout
public int gunType; 
public Texture t;

public Gun(int gunType)
{
if(gunType == 0)
{
damage = 25;
t = new Texture("Pistol ");
clip = 16;
}
else if(gunType == 1)
{
damage = 35;
t = new Texture("AR White");
clip = 30;
}
else if(gunType == 2)
{
damage = 50;
t = new Texture("AR Blue");
clip = 30;
}
else if(gunType == 3)
{
damage = 70;
t = new Texture("AR Gold");
clip = 30;
}
else if(gunType == 4)
{
damage = 20;
t = new Texture("minigun");
clip = 1000;
}
else if(gunType == 5)
{
damage = 5;
t = new Texture("SMG White");
clip = 35;
}
else if(gunType == 6)
{
damage = 10;
t = new Texture("SMG Blue");
clip = 35;
}
else if(gunType == 7)
{
damage = 100;
t = new Texture("sniper blue");
clip = 2;
}
else if(gunType == 8)
{
damage = 250;
t = new Texture("sniper gold");
clip = 2;
}
}
}