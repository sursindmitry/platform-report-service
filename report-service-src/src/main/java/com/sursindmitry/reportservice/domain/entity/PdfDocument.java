package com.sursindmitry.reportservice.domain.entity;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Объект для сохранения PDF файла в MongoDB.
 */
@Getter
@Setter
@Document(collection = "files")
@AllArgsConstructor
@NoArgsConstructor
public class PdfDocument {

    @NotNull(message = "document id cannot be bull")
    private UUID id;

    private String title;

    private String type;

    private Binary file;

    private LocalDateTime created;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PdfDocument that = (PdfDocument) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
