public class Log {
    private String log;

    public Log() {
        log = "";
    }

    public String getLog() {
        if (log.length() == 0)
            return "No Log";
        log = log.replaceAll("\n", " ");
        StringBuilder s = new StringBuilder(log);
        for (int i = 1; i < s.length()/50; i++)
            for (int j = i * 50; j < s.length() && j < (i+1) * 50; j++)
                if (s.charAt(j) == '.' && s.charAt(j + 1) == ' ') {
                    s.insert(j+1, "\n");
                    break;
                }
        return s.toString();
    }

    public void addLog(String log) {
        this.log += log;
    }
}
