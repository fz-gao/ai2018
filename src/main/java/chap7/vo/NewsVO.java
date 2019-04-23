package chap7.vo;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * @author gsd
 */
@Data
public class NewsVO {
    private String title;
    private LocalDateTime newsTime;
    private String newsSource;
    private String detailUrl;
    private LocalDateTime createTime;
    private byte platform;
    private LocalDateTime editTime;
}
