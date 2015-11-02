package br.unifor.android.np2.np2lista1.dao.entidade;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Samuel Santiago on 02/11/2015.
 */
public class PacienteBean implements Parcelable{

    private int id;
    private String nome;
    private String celular;
    private String email;

    public PacienteBean() {
        // TODO
    }

    public PacienteBean(Parcel in) {
        readFromParcelable(in);
    }

    private void readFromParcelable(Parcel in) {
        id = in.readInt();
        nome = in.readString();
        celular = in.readString();
        email = in.readString();
    }


    @SuppressWarnings("rawtypes")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public PacienteBean createFromParcel(Parcel in) {
            return new PacienteBean(in);
        }

        public PacienteBean[] newArray(int size) {
            return new PacienteBean[size];
        }
    };

    @Override
    public int describeContents() {
        //não vai usar
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nome);
        dest.writeString(celular);
        dest.writeString(email);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return "Nome: " + this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static Parcelable.Creator getCREATOR() {
        return CREATOR;
    }

}