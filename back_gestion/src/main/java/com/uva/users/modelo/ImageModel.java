package com.uva.users.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "image_model") 
public class ImageModel {
@Id
@GeneratedValue(strategy =GenerationType.AUTO) 
private Long id;

public Long getId() {
    return this.id;
}

public void setId(Long id) {
    this.id = id;
}

private String name;

public String getName() {
    return this.name;
}

public void setName(String name) {
    this.name = name;
}

private String type;

public String getType() {
    return this.type;
}

public void setType(String type) {
    this.type = type;
}

@Column(length = 50000000)
private byte[] picByte;

public byte[] getPicByte() {
    return this.picByte;
}

public void setPicByte(byte[] picByte) {
    this.picByte = picByte;
}






public ImageModel(String name, String type,byte[] picByte) {
this.name = name;

this.type =type;
this.picByte= picByte;
}
public ImageModel() {
}

}
