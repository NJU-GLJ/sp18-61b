public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double G = 6.67 * 1e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;

    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        return Math.sqrt(
                (this.xxPos - p.xxPos) * (this.xxPos - p.xxPos) + (this.yyPos - p.yyPos) * (this.yyPos - p.yyPos));
    }

    public double calcForceExertedBy(Planet p) {
        if (calcDistance(p) == 0) {
            return 0;
        }
        return G * this.mass * p.mass / (calcDistance(p) * calcDistance(p));
    }

    public double calcForceExertedByX(Planet p) {
        if (calcDistance(p) == 0) {
            return 0;
        }
        return calcForceExertedBy(p) * (p.xxPos - this.xxPos) / calcDistance(p);
    }

    public double calcForceExertedByY(Planet p) {
        if (calcDistance(p) == 0) {
            return 0;
        }
        return calcForceExertedBy(p) * (p.yyPos - this.yyPos) / calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] p) {
        double sumForcex = 0;
        for (int i = 0; i < p.length; i++) {
            sumForcex += this.calcForceExertedByX(p[i]);
        }
        return sumForcex;
    }

    public double calcNetForceExertedByY(Planet[] p) {
        double sumForcey = 0;
        for (int i = 0; i < p.length; i++) {
            sumForcey += this.calcForceExertedByY(p[i]);
        }
        return sumForcey;
    }

    public void update(double dt, double fx, double fy) {
        double ax = fx / this.mass;
        double ay = fy / this.mass;
        this.xxVel += ax * dt;
        this.yyVel += ay * dt;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }

    public void draw() {
        String s = "images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, s);
    }
}
