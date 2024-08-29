public class NBody {
    public static double readRadius(String imgFilename) {
        In in = new In(imgFilename);
        in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String imgFilename) {
        In in = new In(imgFilename);
        Planet[] p = new Planet[in.readInt()];

        in.readDouble();

        for (int i = 0; i < p.length; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            p[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }
        return p;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double R = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        StdDraw.setScale(-R, R);
        // StdDraw.picture(0, 0, "images/starfield.jpg");
        // for (int i = 0; i < planets.length; i++) {
        // planets[i].draw();
        // }
        StdDraw.enableDoubleBuffering();
        for (double t = 0; t <= T; t += dt) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);

            }
            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (int i = 0; i < planets.length; i++) {
                planets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", R);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }

}
