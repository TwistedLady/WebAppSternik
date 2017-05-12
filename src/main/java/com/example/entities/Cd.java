package com.example.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
@Entity
@XmlRootElement
public class Cd {
  @NotNull
  @Id
	private Long id;
//	@NotNull
	private String title;
//	@NotNull
	//@Size(min=3, max=30, message = "Band should be in the range [{min}...{max}]")
	@Pattern(regexp="Motorhead", message="Must be motorhead")
	private String band;
//	@NotNull
	private BigDecimal price;
//	@Size(min=2, max=30, message = "{Size.moneta.opis}")\
	@Size(min=2, max=30, message = "Opis should be in the range [{min}...{max}]")
	private String description;
//	@NotNull
	private Status status;
//	@NotNull
	private Date data;
	public Cd()
	{}
	public Cd(Long id,String title, String band, BigDecimal price, Status status,String description,Date data) {
		super();
		this.id=id;
		this.title = title;
		this.band = band;
		this.price = price;
		this.status = status;
		this.description=description;
		this.data=data;
		final int a; a=4;
	}

	public static Cd produceCd(Long id,String title, String band, BigDecimal price, Status status,String description,Date data)
	{
		return new Cd(id, title,band,price,status,description,data);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBand() {
		return band;
	}

	public void setBand(String band) {
		this.band = band;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((band == null) ? 0 : band.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cd other = (Cd) obj;
		if (band == null) {
			if (other.band != null)
				return false;
		} else if (!band.equals(other.band))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (status != other.status)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cd [id=" + id + ", title=" + title + ", band=" + band + ", price=" + price + ", description="
				+ description + ", status=" + status + ", data=" + data + "]";
	}

}
