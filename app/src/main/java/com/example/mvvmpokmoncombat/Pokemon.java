package com.example.mvvmpokmoncombat;

public class Pokemon {

    private String nombre;
    private int hp;
    private int atk;
    private int def;
    private int atkEsp;
    private int defEsp;

    private Pokemon(String nombre, int hp, int atk, int def, int atkEsp, int defEsp) {
        this.nombre = nombre;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.atkEsp = atkEsp;
        this.defEsp = defEsp;
    }

    public Pokemon(){
        //vacio para el viewModel
    }

    interface Callback {
        void cuandoHayaErrorEnNombre(String errNombre);
        void cuandoHayaErrorEnVida(String errHp);
        void cuandoHayaErrorEnAtaque(String errAtk);
        void cuandoHayaErrorEnDefensa(String errDef);
        void cuandoHayaErrorEnAtaqueEspecial(String errAtkEsp);
        void cuandoHayaErrorEnDefensaEspecial(String errDefEsp);
        void cuandoTermineDeValidar(boolean validado, Pokemon pokemonV);

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getAtkEsp() {
        return atkEsp;
    }

    public void setAtkEsp(int atkEsp) {
        this.atkEsp = atkEsp;
    }

    public int getDefEsp() {
        return defEsp;
    }

    public void setDefEsp(int defEsp) {
        this.defEsp = defEsp;
    }

    public void crearPokemon(String nombre, int hp, int atk, int def, int atkEsp, int defEsp, Callback callback) {
        int min = 0;
        int max = 999;

        boolean error = false;

        if (nombre.isEmpty() || nombre.matches("^\\s*$")){
            callback.cuandoHayaErrorEnNombre("Tienen que haber caracteres");
            error = true;
        }

        if (comprobarLimites(hp, min, max)){
            callback.cuandoHayaErrorEnVida(hp + " tiene que estar entre " + min +" - " + max);
            error = true;
        }

        if (comprobarLimites(atk, min, max)){
            callback.cuandoHayaErrorEnAtaque(atk + " tiene que estar entre " + min +" - " + max);
            error = true;
        }

        if (comprobarLimites(def, min, max)){
            callback.cuandoHayaErrorEnDefensa(def + " tiene que estar entre " + min +" - " + max);
            error = true;
        }

        if (comprobarLimites(atkEsp, min, max)){
            callback.cuandoHayaErrorEnAtaqueEspecial(atkEsp + " tiene que estar entre " + min +" - " + max);
            error = true;
        }

        if (comprobarLimites(defEsp, min, max)){
            callback.cuandoHayaErrorEnDefensaEspecial(defEsp + " tiene que estar entre " + min +" - " + max);
            error = true;
        }

        if (!error) {
            callback.cuandoTermineDeValidar(true, new Pokemon(nombre, hp, atk, def, atkEsp, defEsp));
        }

    }

    private static boolean comprobarLimites(int num, int min, int max) {
        return num < min || num > max;
    }
}

