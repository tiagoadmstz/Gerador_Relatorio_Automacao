/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.util;

import java.math.BigDecimal;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.MonthDay;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author tiago.teixeira
 */
public class Datas {

    public static String calcularIdade(String dataNascimento) {

        Date dataAtual = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dataAtual);
        Format format = new SimpleDateFormat("MM");
        BigDecimal temp;
        String strData[];

        strData = dataNascimento.split("/");
        Integer anoNascimento = Integer.parseInt(strData[2]);

        if ((Integer.parseInt(format.format(c.getTime())) + 1) > Integer.parseInt(strData[1])) {
            BigDecimal anoAtual = new BigDecimal(Year.now().toString());
            temp = anoAtual.subtract(new BigDecimal(anoNascimento));
            return temp.toString();
        } else {
            BigDecimal anoAtual = new BigDecimal(Year.now().toString());
            anoAtual = anoAtual.subtract(new BigDecimal("1"));
            temp = anoAtual.subtract(new BigDecimal(anoNascimento));
            return temp.toString();
        }
    }

    /**
     * Função que manipula String no formato de hora "HH:mm" facilitando para o
     * usuário no momento da digitação ou retorna a hora atual.
     * <br>
     * Exemplo: <br>
     * Entrada do usuário: 1:12 ou 112<br>
     * Devolve: 01:12<br>
     *
     * @param hora valor do campo de hora
     * @return String Hora Formatada
     * @param solicitacao int <br><b>0</b> para hora atual<br><b>1</b> para
     * manipulação de hora
     */
    public static String getHour(String hora, int solicitacao) {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("HH:mm");
        String[] h = null;
        String resultado = "";

        try {
            //verifica a solicitação do usuário
            if (solicitacao == 0) {
                return f.format(date);
            } else {
                //verifica se a variável passada é nula ou vazia
                if (hora != null || !"".equals(hora)) {
                    //verifica se a variável contêm o ponto de separação
                    if (hora.contains(":")) {
                        h = hora.split(":");
                        //verifica a quantidade de digitos e se a posição do dois pontos
                        resultado = hora.indexOf(":") == 1 ? "0" : "";
                        //loop para setar a variável de saída
                        for (int i = 0; i < h.length; i++) {
                            if (i == 1 && hora.indexOf(":") == 1 && hora.length() == 3 || i == 1 && hora.indexOf(":") == 2 & hora.length() == 4) {
                                resultado += ":0";
                            } else if (i == 1 && hora.length() == 5 && hora.indexOf(":") == 2) {
                                resultado += ":";
                            }
                            resultado += h[i];
                        }
                    } else {
                        char[] letras = hora.toCharArray();
                        resultado = hora.length() == 2 ? "0" : hora.indexOf("0") != 0 && hora.length() == 3 ? "0" : hora.indexOf("0") == 2 && hora.length() == 3 ? "0" : "";
                        for (int i = 0; i < letras.length; i++) {
                            if (hora.length() == 2 && i == 1 && hora.length() < 4) {
                                resultado += ":0";
                            } else if (i == 2 && hora.length() == 3 && hora.indexOf("0") == 0) {
                                resultado += ":0";
                            } else if (i == 2 && hora.length() == 4) {
                                resultado += ":";
                            } else if (i == 1 && letras.length == 3 && hora.indexOf("0") != 0) {
                                resultado += ":";
                            }
                            resultado += String.valueOf(letras[i]);
                        }
                    }
                }
                return verificaHora(resultado) ? resultado : f.format(date);
            }
        } catch (Exception ex) {
            return f.format(date);
        }
    }

    private static boolean verificaHora(String hora) {
        String hm[] = hora.split(":");
        int h = Integer.parseInt(hm[0]);
        int m = Integer.parseInt(hm[1]);
        boolean resultado;

        resultado = h >= 0 && h <= 24;
        if (resultado) {
            if (h == 24 && m == 0) {
                resultado = true;
            } else if (h <= 23) {
                resultado = m >= 0 && m <= 59;
            } else {
                resultado = false;
            }
        }

        return resultado;
    }

    /**
     * Função que manipula String no formato de data "dd/MM/yyyy" facilitando
     * para o usuário no momento da digitação ou retorna a data atual.
     * <br>
     * Exemplo: <br>
     * Entrada do usuário: 1/1/15<br>
     * Devolve: 01/01/2015<br>
     *
     * @return String data
     * @param solicitacao int <br><b>0</b> para data atual<br><b>1</b> para
     * manipulação de data
     */
    public static String getDateString(String data, int solicitacao) {

        String temp = "";
        String strData[] = null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date x = new Date();

        if (data != null || !"".equals(data) && solicitacao == 1) {
            try {

                if (data.contains("/")) {
                    strData = data.split("/");
                } else {
                    strData = new String[1];
                    strData[0] = "";
                }

                if (strData.length != 3) {
                    if (strData.length == 2) {
                        temp = dinamicDate(strData[0], strData[1], "0");
                    } else {
                        temp = formato.format(x);
                    }
                } else {
                    temp = dinamicDate(strData[0], strData[1], !"".equals(strData[2]) ? strData[2] : "0");
                }
            } catch (Exception excep) {
                temp = formato.format(x);
            }
        } else if (solicitacao == 0) {
            temp = formato.format(x);
        }

        return temp;
    }

    /**
     * Este método converte uma data informada como String em um objeto do tipo
     * Date.
     *
     * @param data String contendo a data no formato "dd/mm/yyyy"
     * @return Objecto do tipo Date
     */
    public static Date getDate(String data) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dt = null;

        if (data.length() == 10) {
            try {
                dt = sdf.parse(data);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro na conversão", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(Datas.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Datas.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Informe uma data válida", "Erro na conversão", JOptionPane.ERROR_MESSAGE);
        }

        return dt;
    }

    public static String dinamicDate(String dia, String mes, String ano) {
        return verificarDia(dia) + "/" + verificarMes(mes) + "/" + verificarAno(ano);
    }

    public static String verificarDia(String dia) {
        try {
            String result = "";
            int dia_int = Integer.parseInt(dia);
            if (dia_int > 0 && dia_int < 31) {
                result = dia_int < 10 ? result + "0" + dia_int : result + dia_int;
            } else {
                result = result + "31";
            }
            return result;
        } catch (Exception ex) {
            return MonthDay.now().format(DateTimeFormatter.ofPattern("dd"));
        }
    }

    public static String verificarMes(String mes) {
        try {
            String result = "";
            int mes_int = Integer.parseInt(mes);
            if (mes_int > 0 && mes_int < 13) {
                result = mes_int < 10 ? result + "0" + mes_int : result + mes_int;
            } else {
                result = result + "12";
            }
            return result;
        } catch (Exception ex) {
            return YearMonth.now().format(DateTimeFormatter.ofPattern("MM"));
        }
    }

    public static String verificarAno(String ano) {
        try {
            String result = "";
            int ano_int = Integer.parseInt(ano);
            long ano_atual = Long.parseLong(Year.now().toString());
            String ano_user = ano_int != 0 ? Integer.toString(ano_int) : Year.now().toString();
            long diferenca_atual_user = ano_atual - ano_int;

            if (ano_user.length() > 0 && ano_user.length() == 2) {
                result = diferenca_atual_user >= 2000 ? result + "20" + ano_user : result + "19" + ano_user;
            } else if (ano_user.length() > 0 && ano_user.length() == 1) {
                result = diferenca_atual_user >= 2000 ? result + "200" + ano_user : result + "190" + ano_user;
            } else if (ano_user.length() == 3 || ano_user.length() >= 5) {
                result = result + Year.now().toString();
            } else {
                result = result + ano_user;
            }
            return result;
        } catch (Exception ex) {
            return Year.now().toString();
        }
    }

}
