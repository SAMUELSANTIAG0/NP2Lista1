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
    private String parenteCelular;
    private String telefone;
    private String endereco;

    public PacienteBean() {
        // TODO
    }

    public String getParenteCelular() {
        return parenteCelular;
    }

    public void setParenteCelular(String parenteCelular) {
        this.parenteCelular = parenteCelular;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public static PacienteBean paciente1 = new PacienteBean();
    public static PacienteBean paciente2 = new PacienteBean();
    public static PacienteBean paciente3 = new PacienteBean();
    public static PacienteBean paciente4 = new PacienteBean();
    public static PacienteBean paciente5 = new PacienteBean();
    public static PacienteBean paciente0 = new PacienteBean();



    public void setEndereco(String endereco) {
        this.endereco = endereco;
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
        return this.nome;
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


   public void addDados(String endereco, String celular, String telefone, String email, String parenteCelular){
       this.endereco = endereco;
       this.celular = celular;
       this.telefone = telefone;
       this.email = email;
       this.parenteCelular = parenteCelular;
    }

    public static Parcelable.Creator getCREATOR() {
        return CREATOR;
    }

}
