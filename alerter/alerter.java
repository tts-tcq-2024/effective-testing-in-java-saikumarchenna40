public class alerter {
    static int alertFailureCount = 0;

    // Flag to simulate network failure
    static boolean simulateNetworkFailure = false;

    static int networkAlertStub(float celcius) {
        System.out.println("ALERT: Temperature is " + celcius + " celcius");
        // Simulate a failure based on the flag
        return simulateNetworkFailure ? 500 : 200;
    }

    static void alertInCelcius(float farenheit) {
        float celcius = (farenheit - 32) * 5 / 9;
        int returnCode = networkAlertStub(celcius);
        if (returnCode != 200) {
            // Count the failure correctly
            alertFailureCount += 1;  // Increment failure count on non-ok response
        }
    }

    public static void main(String[] args) {
        // Test case: Initially, we simulate a successful network call
        alertInCelcius(400.5f);
        alertInCelcius(303.6f);
        
        // Show count of failed alerts before simulating a failure
        System.out.printf("%d alerts failed.\n", alertFailureCount);
        
        // Simulate network failure
        simulateNetworkFailure = true;
        
        // Test case: Now, with simulated network failure
        alertInCelcius(400.5f);
        alertInCelcius(303.6f);
        
        // Show count of failed alerts after simulating failure
        System.out.printf("%d alerts failed after simulation.\n", alertFailureCount);
        
        System.out.println("All is well (maybe!)\n");
    }
}
