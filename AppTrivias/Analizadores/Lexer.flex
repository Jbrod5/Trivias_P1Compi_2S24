/* - - - - - - - - - - - - - - - - - CODIGO DE USUARIO - - - - - - - - - - - - - - - - - */
package com.jbrod.apptrivias.analizadores;
import java_cup.runtime.*;

%%

/*  - - - - - - - - - - - - - - - - - DECLARACIONES  - - - - - - - - - - - - - - - - - */

%public 
%class Lexer
%cup
%line
%column

numero = [0-9]+
string = [a-zA-Z0-9\¿][a-zA-Z0-9\¿\?\s]*
//string = [a-zA-Z0-9\¿][a-zA-Z0-9:\¿\?\s]*
identificador = ([a-zA-Z]|"_"|"-"|"$")([a-zA-Z0-9]|"_"|"-"|"$")*

menque = "<"
mayque = ">"
exclam = "!"
dospun = ":"
llavop = "{"
llavcl = "}"
coropn = "["
corcls = "]"
paropn = "("
parcls = ")" 
comma  = ","
qstmrk = "?"
comill = "\""|"“"|"”"
orsymb = "|" 
encabezado = "<?xson version=\"1.0\" ?>"
LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [ \t\f\s]


trivia = "trivia"
id_tri = "ID_TRIVIA"
nombre = "NOMBRE"
creado = "CREADOR"
fecha  = "FECHA"
tiempo = "TIEMPO"
tema   = "TEMA"

campo_t = "CAMPO_TEXTO"
area_te = "AREA_TEXTO"
checkbo = "CHECK-BOX"
radio   = "RADIO"
fichero = "FICHERO"
combo   = "COMBO"

param_tri = "TRIVIA"
param_ind = "INDICE"
param_tex = "TEXTO_VISIBLE"
param_res = "RESPUESTA"
param_opc = "OPCIONES"
param_fil = "FILAS"
param_col = "COLUMNAS"



%{
    private Symbol symbol(int type){
        return new Symbol(type, yyline +1, yycolumn +1);
    }

    private Symbol symbol(int type, Object value){
        System.out.println("Token con valor reconocido: " + yytext());
        return new Symbol(type, yyline +1, yycolumn + 1, value);
    }

    private void error (String message){
        System.out.println("Error en la linea: " + (yyline +1) + " columna: " + (yycolumn +1) + " : " + message);
    }
%}

%%

/*  - - - - - - - - - - - - - - - - - REGLAS LEXICAS  - - - - - - - - - - - - - - - - - */

"CHECK-BOX" { 
        // Acción a realizar cuando se encuentra "CHECK-BOX"
        return symbol(sym.CHECKBO); 
    }
"RADIO" { return symbol(sym.RADIO  ); }
{checkbo}   { return symbol(sym.CHECKBO); }
{campo_t}   { return symbol(sym.CAMPO_T); }
{area_te}   { return symbol(sym.AREA_TE); }
{radio}     { return symbol(sym.RADIO  ); }
{fichero}   { return symbol(sym.FICHERO); }
{combo}     { return symbol(sym.COMBO  ); }

{trivia}        { return symbol(sym.TRIVIA); } 
{id_tri}        { return symbol(sym.ID_TRI); }
{nombre}        { return symbol(sym.NOMBRE); }
{creado}        { return symbol(sym.CREADO); }
{fecha}         { return symbol(sym.FECHA ); }
{tiempo}        { return symbol(sym.TIEMPO); }
{tema}          { return symbol(sym.TEMA  ); }

{param_tri} { return symbol(sym.PARAM_TRI); }
{param_ind} { return symbol(sym.PARAM_IND); }
{param_tex} { return symbol(sym.PARAM_TEX); }
{param_res} { return symbol(sym.PARAM_RES); }
{param_opc} { return symbol(sym.PARAM_OPC); }
{param_fil} { return symbol(sym.PARAM_FIL); }
{param_col} { return symbol(sym.PARAM_COL); }

{menque}          { return symbol(sym.MENQUE); }          
{mayque}          { return symbol(sym.MAYQUE); }            
{exclam}          { return symbol(sym.EXCLAM); }            
{dospun}          { return symbol(sym.DOSPUN); }                        
{llavop}          { return symbol(sym.LLAVOP); }            
{llavcl}          { return symbol(sym.LLAVCL); }            
{coropn}          { return symbol(sym.COROPN); }            
{corcls}          { return symbol(sym.CORCLS); }            
{paropn}          { return symbol(sym.PAROPN); }
{parcls}          { return symbol(sym.PARCLS); } 
{comma}           { return symbol(sym.COMMA);  }
{qstmrk}          { return symbol(sym.QSTMRK); }
{comill}          { return symbol(sym.COMILL); }
{orsymb}          { return symbol(sym.ORSYMB); }

{encabezado}      { return symbol(sym.ENCABEZADO); }
{WhiteSpace}      { /* Ignorar */}
{identificador}   { return symbol(sym.IDENTIFICADOR, yytext());                   }
{string}          { return symbol(sym.STRING, yytext());                          }
{numero}          { return symbol(sym.NUMERO       , Integer.parseInt(yytext())); }

[^]            { System.out.println("No se reconocio el lexema " + yytext() + " como un token valido y se ignoro.");
                 //errores.agregarError(yytext(), yyline +1, yycolumn + 1, "Lexico", "El simbolo no se encuentra definido en el alfabeto.");
                 }
<<EOF>>        { return symbol(sym.EOF); }
