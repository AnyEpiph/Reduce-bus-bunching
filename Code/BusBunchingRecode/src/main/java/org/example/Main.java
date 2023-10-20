package org.example;
import static org.example.Output.*;

public class Main {

    /**
     * parameters
     */
    public static int departure_interval;
    public static int consider_range_u ;
    public static double arrival_rate_lambda;
    public static int I_max;
    public static int I_min;
    public static boolean use_real_world_data;
    /**
     * Definition
     */
    public static final int NO_CONTROL = 0;
    public static final int AT_STATION_CONTROL = 1;
    public static final int INTER_STATION_CONTROL = 2;
    public static final int JOINT_CONTROL = 3;
    public static final int BASE_MINIMUM_HEADWAY = 4;
    public static final int BASE_QUADRATIC_PROBLEM = 5;
    public static final int BASE_HE = 6;

    public static void main(String[] args) throws Exception {

//        departure_interval = 500;
//        consider_range_u = 5;
//        arrival_rate_lambda = 0.03;
//        I_max = 60;
//        I_min = 0;
//        Simulator.STATION_AMOUNT = 49;
//        Simulator.BUS_AMOUNT = 150;
//        plotTimePositionRelation(true, JOINT_CONTROL);
//        plotTimePositionRelation(true, BASE_QUADRATIC_PROBLEM);
//        plotTimePositionRelation(true, BASE_MINIMUM_HEADWAY);
//        plotTimePositionRelation(true, BASE_HE);

        Output output = new Output();
//        output.exportNumericalResults_numerical();
        output.exportNumericalResults_real();
//        output.exportNumericalResults_byStation();
//        output.sensitivityAnalysis_ArrivalRate();
//        output.sensitivityAnalysis_Imax();
//        output.sensitivityAnalysis_ImaxImin();
//        output.sensitivityAnalysis_ConsiderationRangeU();
//        output.runtimeAnalysis();

    }

    /**
     * aa
     * @param
     */
    private static void generalTest(boolean real_world_data, int control_method) throws Exception {
        System.out.println("* Control Method == " + getMethodName(control_method) + " *");
        Simulator simulator = new Simulator(real_world_data, false, departure_interval,consider_range_u,arrival_rate_lambda,I_max,I_min);
        simulator.simulation(control_method);
        double[] result = showGeneralResult(simulator);
        System.out.println("Board & Total Passenger : " + (int)result[0] + " / " + (int)result[1]);
        System.out.println("PBR = " + result[2]);
        System.out.println("AWT = " + result[3]);
    }

    private static void plotTimePositionRelation(boolean real_world_data, int control_method) throws Exception {
        System.out.println("* Control Method == " + getMethodName(control_method) + " *");
        Simulator.STATION_AMOUNT = 49;
        Simulator simulator = new Simulator(real_world_data, true, departure_interval,consider_range_u,arrival_rate_lambda,I_max,I_min);
        simulator.simulation(control_method);
        double[] result = showGeneralResult(simulator);
        System.out.println("PBR = " + result[2]);
        System.out.println("AWT = " + result[3]);
        timePositionFigure(control_method,simulator);
    }

    public static String getMethodName(int control_method){
        String control_method_name = null;
        switch (control_method){
            case NO_CONTROL:
                control_method_name = "NoControl";
                break;
            case AT_STATION_CONTROL:
                control_method_name = "AtStationControl";
                break;
            case INTER_STATION_CONTROL:
                control_method_name = "InterStationControl";
                break;
            case JOINT_CONTROL:
                control_method_name = "JointControl";
                break;
            case BASE_MINIMUM_HEADWAY:
                control_method_name = "MinimumHeadway(Baseline_1)";
                break;
            case BASE_QUADRATIC_PROBLEM:
                control_method_name = "QuadraticEberlein(Baseline_2)";
                break;
            default:
                control_method_name = "He(Baseline_3)";
        }
        String method_name = control_method_name;
        return method_name;
    }

}