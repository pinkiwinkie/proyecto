package com.proyectofinal.proyecto.repository.model;

import com.mysql.cj.jdbc.Blob;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Oficio implements Serializable {
    private int id;
    private String description,
            imageUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Oficio oficio = (Oficio) o;
        return id == oficio.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
