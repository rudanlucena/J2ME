import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class Calculadora extends MIDlet implements CommandListener {
    public Display display;
    public Form formCalc;
    public Alert alertaDivisaoPorZero;
    public TextField label1, label2;
    public StringItem resposta;
    public Command cmdSair, cmdVoltar, cmdSomar, cmdDividir, cmdSubtrair, cmdMultiplicar;
    public int n1, n2, resultado;

    public void startApp() {
        display = Display.getDisplay(this);
        
        label1 = new TextField("N1:", null, 5, TextField.NUMERIC);
        label2 = new TextField("N2:", null, 5, TextField.NUMERIC);
        cmdSair = new Command("Sair", Command.BACK, 1);
        cmdVoltar = new Command("Voltar", Command.BACK, 0);
        cmdSomar = new Command("Somar", Command.ITEM, 2);
        cmdSubtrair = new Command("Subtrair", Command.ITEM, 3);
        cmdMultiplicar = new Command("Multiplicar", Command.ITEM, 4);
        cmdDividir = new Command("Dividir", Command.ITEM, 5);
        resposta = new StringItem("R: ", "-");
        
        //Adicionando ao formulario
        formCalc = new Form("App Calc");
        formCalc.append(label1);
        formCalc.append(label2);
        formCalc.append(resposta);

        //Comandos
        formCalc.addCommand(cmdSair);
        formCalc.addCommand(cmdSomar);
        formCalc.addCommand(cmdSubtrair);
        formCalc.addCommand(cmdMultiplicar);
        formCalc.addCommand(cmdDividir);
        
        alertaDivisaoPorZero = new Alert("Operação Invalida", "Não é possivel fazer a divisão por zero", null, AlertType.ERROR);
        alertaDivisaoPorZero.addCommand(cmdVoltar);

        formCalc.setCommandListener(this);
        alertaDivisaoPorZero.setCommandListener(this);
        display.setCurrent(formCalc);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
        notifyDestroyed();
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdSair) 
            destroyApp(true);
        else if(c == cmdVoltar)
            display.setCurrent(formCalc);
        else {
            if ((label1.getString().length() > 0) && (label2.getString().length() > 0)) {
                converte();
                if (c == cmdSomar) 
                    resultado = n1 + n2;
                else if (c == cmdSubtrair) 
                    resultado = n1 - n2;
                else if (c == cmdMultiplicar) 
                    resultado = n1 * n2;
                else if (c == cmdDividir){
                    if(n2 == 0){
                        alertaDivisaoPorZero.setTimeout(Alert.FOREVER);
                        display.setCurrent(alertaDivisaoPorZero);
                    }else
                        resultado = n1 / n2;
                } 
                
                resposta.setText(String.valueOf(resultado));
            } 
        }
    }

    public void converte() {
            n1 = Integer.parseInt(label1.getString());
            n2 = Integer.parseInt(label2.getString());
    }
}