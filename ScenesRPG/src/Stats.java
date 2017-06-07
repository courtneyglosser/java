
package cglosser;


import java.awt.Font;
import java.awt.FontMetrics;
import java.text.DecimalFormat;


public class Stats {
    // record stats every 1 second (roughly)
    private static int MAX_STATS_INTERVAL = 1000;
    // number of FPS values stored to get an average
    private static int NUM_FPS = 10;


    private int statsInterval = 0;    // in ms
    private long prevStatsTime = 0;

    private int gameStartTime;
    private long timeSpentInGame = 0;    // in seconds
    private int totalElapsedTime = 0;

    private int framesSkipped = 0;
    private int totalFramesSkipped = 0;
    private int period = 1000/100; // ms / FPS;

    private double fpsStore[];
    private int statsCount = 0;
    private double averageFPS = 0.0;

    private double upsStore[];
    private double averageUPS = 0.0;

    private int frameCount = 0;


    private DecimalFormat df = new DecimalFormat("0.##");  // 2 dp
    private DecimalFormat timedf = new DecimalFormat("0.####");  // 4 dp

    private Font font;
    private FontMetrics metrics;

    public Stats() {
        // Timing elements
        fpsStore = new double[NUM_FPS];
        upsStore = new double[NUM_FPS];

        for (int i=0; i < NUM_FPS; i++) {
            fpsStore[i] = 0.0;
            upsStore[i] = 0.0;
        }

    }

    /**
        The statistics:
        - the summed periods for all the iterations in this interval
        (period is the amount of time a single frame iteration should take),
        the actual elapsed time in this interval,
        the error between these two numbers;

        - the total frame count, which is the total number of calls to run();

        - the frames skipped in this interval, the total number of frames
        skipped. A frame skip is a game update without a corresponding render;

        - the FPS (frames/sec) and UPS (updates/sec) for this interval,
        the average FPS & UPS over the last NUM_FPSs intervals.

        The data is collected every MAX_STATS_INTERVAL  (1 sec).
     */
    public void storeStats()
    {
        frameCount++;
        statsInterval += period;

        if (prevStatsTime == 0) {
            prevStatsTime = System.currentTimeMillis();
        }

        // record stats every MAX_STATS_INTERVAL
        if (statsInterval >= MAX_STATS_INTERVAL) {
            long timeNow = System.currentTimeMillis();
            timeSpentInGame = (timeNow - gameStartTime);

            // time since last stats collection
            long realElapsedTime = timeNow - prevStatsTime;
            totalElapsedTime += realElapsedTime / 1000;

            double timingError =
            ((double)(realElapsedTime - statsInterval) / statsInterval) * 100.0;

            totalFramesSkipped += framesSkipped;

            // calculate the latest FPS and UPS
            double actualFPS = 0;
            double actualUPS = 0;
            if (totalElapsedTime > 0) {
                actualFPS = ((double)frameCount / totalElapsedTime);
                actualUPS = ((double)(frameCount + totalFramesSkipped) / totalElapsedTime);
            }

            // store the latest FPS and UPS
            fpsStore[ (int)statsCount%NUM_FPS ] = actualFPS;
            upsStore[ (int)statsCount%NUM_FPS ] = actualUPS;
            statsCount++;

            // total the stored FPSs and UPSs
            double totalFPS = 0.0;
            double totalUPS = 0.0;

            for (int i=0; i < NUM_FPS; i++) {
                totalFPS += fpsStore[i];
                totalUPS += upsStore[i];
            }

            // obtain the average FPS and UPS
            if (statsCount < NUM_FPS) {
                averageFPS = totalFPS/statsCount;
                averageUPS = totalUPS/statsCount;
            }
            else {
                averageFPS = totalFPS/NUM_FPS;
                averageUPS = totalUPS/NUM_FPS;
            }

            /**/
            System.out.println(timedf.format( (double) statsInterval/1000) + " " +
            timedf.format((double) realElapsedTime/1000) + "s " +
            df.format(timingError) + "% " +
            frameCount + "c " +
            framesSkipped + "/" + totalFramesSkipped + " skip; " +
            df.format(actualFPS) + " " + df.format(averageFPS) + " afps; " +
            df.format(actualUPS) + " " + df.format(averageUPS) + " aups" );
            /**/


            framesSkipped = 0;
            prevStatsTime = timeNow;
            statsInterval = 0;   // reset
        }
    }  // end of storeStats()

    public void printStats()
    {
        System.out.println("Frame Count/Loss: " + frameCount + " / " + totalFramesSkipped);
        System.out.println("Average FPS: " + df.format(averageFPS));
        System.out.println("Average UPS: " + df.format(averageUPS));
        System.out.println("Time Spent: " + timeSpentInGame + " secs");
    }  // end of printStats()

}
