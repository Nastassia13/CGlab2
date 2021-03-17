import java.util.Objects;

public class GraphicFile {
    private String name;
    private String size;
    private String resolution;
    private String colorDepth;
    private String compression;

    public GraphicFile(String name, String size, String resolution, String colorDepth, String compression) {
        this.name = name;
        this.size = size;
        this.resolution = resolution;
        this.colorDepth = colorDepth;
        this.compression = compression;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getColorDepth() {
        return colorDepth;
    }

    public void setColorDepth(String colorDepth) {
        this.colorDepth = colorDepth;
    }

    public String getCompression() {
        return compression;
    }

    public void setCompression(String compression) {
        this.compression = compression;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GraphicFile graphicFile = (GraphicFile) o;
        return name.equals(graphicFile.name) && size.equals(graphicFile.size)
                && resolution.equals(graphicFile.resolution) && colorDepth.equals(graphicFile.colorDepth)
                && compression.equals(graphicFile.compression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size, resolution, colorDepth, compression);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GraphicFile{");
        sb.append("fileName='").append(name).append('\'');
        sb.append(", imageSize='").append(size).append('\'');
        sb.append(", resolution='").append(resolution).append('\'');
        sb.append(", colorDepth='").append(colorDepth).append('\'');
        sb.append(", compression='").append(compression).append('\'');
        sb.append('}');
        return sb.toString();
    }
}