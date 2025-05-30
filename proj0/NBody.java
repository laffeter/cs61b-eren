public class NBody{
	private static int N;
	private static double radius;
	public static double readRadius(String location){
		In in = new In(location);
		N = in.readInt();
		radius= in.readDouble();
		return radius;
	}
	public static Planet[] readPlanets(String location){
		readRadius(location);
		In in = new In(location);
		in.readInt();
		in.readDouble();
		Planet[] planets = new Planet[N];
		for(int i=0;i<N;i+=1){
			double xpos=in.readDouble();
			double ypos=in.readDouble();
			double xvel=in.readDouble();
			double yvel=in.readDouble();
			double mass=in.readDouble();
			String pName=in.readString();
			planets[i]=new Planet(xpos,ypos,xvel,yvel,mass,pName);
		}
		return planets;
	}
	public static void main(String[] args){
		StdDraw.enableDoubleBuffering();
		double T = Double.valueOf(args[0]);
		double dt = Double.valueOf(args[1]);
		String filename = args[2];
		Planet[] planets = readPlanets(filename);
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		double sTime=0;
		while(sTime<=T){
			StdDraw.clear();
			double[] xForces= new double[N];
			double[] yForces= new double[N];
			for(int i=0;i<N;i+=1){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for(int i=0;i<N;i++){
				planets[i].update(dt, xForces[i], yForces[i]);
			}
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for(Planet pla : planets){
				pla.draw();
			}
			StdDraw.show();
			StdDraw.pause(3);

			sTime += dt;
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
	}

	}
}