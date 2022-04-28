package AccountModelPkg;

public class AccessLevel {
    public int accessId;
    public String accessType;

    @Override
    public String toString() {
        return "AccessLevelMod{" +
                "accessId=" + accessId +
                ", accessType='" + accessType + '\'' +
                '}';
    }
}
