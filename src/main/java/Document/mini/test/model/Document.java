package Document.mini.test.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "document")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Tên không được để trống")
    @Size(min = 5, max = 100, message = "Tên phải có từ 5 đến 100 ký tự")
    private String name;

    @NotNull(message = "Năm không được để trống")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date year;

    @NotEmpty(message = "Mô tả không được để trống")
    private String description;

    @NotNull(message = "Phải chọn loại tài liệu")
    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private Type type;

    public Document() {}

    public Document(Long id, String name, Date year, String description, Type type) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.description = description;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}