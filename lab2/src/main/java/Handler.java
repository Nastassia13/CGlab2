import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Handler {
    private File file;
    private String widthTag = "Image Width";
    private String heightTag = "Image Height";
    private String resolutionTag = "X Resolution";
    private String colorDepth1Tag = "Data Precision";
    private String colorDepth2Tag = "Bits Per Sample";
    private String compressionTag = "Compression";
    private Metadata metadata;

    public Handler(File file) {
        this.file = file;
        try {
            metadata = ImageMetadataReader.readMetadata(file);
        } catch (ImageProcessingException | IOException e) {
            e.printStackTrace();
        }
    }

    public String getFileName() {
        return file.getName();
    }

    public String getImageSize() {
        final List<String> list = new LinkedList<>();
        metadata.getDirectories()
                .iterator()
                .forEachRemaining(p -> p.getTags()
                        .iterator()
                        .forEachRemaining(x -> {
                            if ((x.getTagName().equals(widthTag) || x.getTagName().equals(heightTag)) && list.size() != 2) {
                                list.add(x.getDescription());
                            }
                        }));
        if (list.size() == 2) {
            return list.get(0).split("\\s+")[0] + " x " + list.get(1).split("\\s+")[0];
        } else {
            return "-";
        }
    }

    public String getColorDepth() {
        StringBuilder sb = new StringBuilder();
        metadata.getDirectories()
                .iterator()
                .forEachRemaining(p -> p.getTags()
                        .iterator()
                        .forEachRemaining(x -> {
                            if ((x.getTagName().equals(colorDepth2Tag) || x.getTagName().equals(colorDepth1Tag)) && sb.length() == 0) {
                                sb.append(x.getDescription());
                            }
                        }));
        if (sb.length() == 0) {
            return "-";
        } else {
            return sb.toString().split("\\s+")[0];
        }
    }

    public String getCompression() {
        StringBuilder sb = new StringBuilder();
        metadata.getDirectories()
                .iterator()
                .forEachRemaining(p -> p.getTags()
                        .iterator()
                        .forEachRemaining(x -> {
                            if (x.getTagName().equals(compressionTag) && sb.length() == 0) {
                                sb.append(x.getDescription());
                            }
                        }));
        if (sb.length() == 0) {
            return "-";
        } else {
            return sb.toString();
        }
    }

    public String getResolution() {
        StringBuilder sb = new StringBuilder();
        metadata.getDirectories()
                .iterator()
                .forEachRemaining(p -> p.getTags()
                        .iterator()
                        .forEachRemaining(x -> {
                            if (x.getTagName().equals(resolutionTag) && sb.length() == 0) {
                                sb.append(x.getDescription());
                            }
                        }));
        if (sb.length() == 0) {
            return "-";
        } else {
            return sb.toString();
        }
    }
}
