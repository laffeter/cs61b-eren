public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel, yyVel;
	public double mass;
	public String imgFileName;
	private static double grav= 6.67e-11; 
	public Planet(double xP, double yP, double xV,
				double yV, double m, String img)
	{
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;

	}
	public Planet(Planet b){
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;

	}
	public double calcDistance(Planet pla){
		double dx = this.xxPos - pla.xxPos;
		double dy = this.yyPos - pla.yyPos;
		double r = Math.pow((dx*dx + dy*dy), 0.5);
		return r;
		}
	public double calcForceExertedBy(Planet pla){
		double force = (grav * this.mass * pla.mass) / (this.calcDistance(pla)*this.calcDistance(pla));
		return force;
	}
	public double calcForceExertedByX(Planet pla){
		double forceX = (this.calcForceExertedBy(pla) * (pla.xxPos - this.xxPos)) / this.calcDistance(pla);
		return forceX; 
	}
	public double calcForceExertedByY(Planet pla){
		double forceY = (this.calcForceExertedBy(pla) * (pla.yyPos - this.yyPos)) / this.calcDistance(pla);
		return forceY;
	}
	public double calcNetForceExertedByX(Planet[] planets){
		double netForceX=0.0;
		for(Planet planet : planets){
			if(!this.equals(planet)){
				netForceX += this.calcForceExertedByX(planet);
			}
		}
		return netForceX;
	}
	public double calcNetForceExertedByY(Planet[] planets){
		double netForceY=0.0;
		for(Planet planet : planets){
			if(!this.equals(planet)){
				netForceY += this.calcForceExertedByY(planet);
			}
		}
		return netForceY;
	}
	public void update(double dt, double fX, double fY){
		double fXvel = (dt * fX)/mass;
		double fYvel = (dt * fY)/mass;
		xxVel += fXvel;
		yyVel += fYvel;
		xxPos += xxVel*dt;
		yyPos += yyVel*dt;
	}
	public void draw(){
		String loc="images/";
		loc = loc.concat(this.imgFileName);
		StdDraw.picture(this.xxPos, this.yyPos, loc);
	}
}	