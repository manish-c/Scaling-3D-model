import java.io.*;
import java.util.Scanner;

public class Wavefront {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter all scaling vectorVal for x y z axis space seperated(enter same values for uniform scaling): ");
        String scalei = sc.nextLine();
        String temparray[] = scalei.split(" ");
        Float scale[] = new Float[temparray.length];
        for (int i = 0; i < scale.length; ++i) {
            scale[i] = Float.parseFloat(temparray[i]);
        }

        try {
            File inpo = new File("obj/test.obj");
            FileReader fr = new FileReader(inpo);

            File outo = new File("obj/output.obj");
            FileWriter fw = new FileWriter(outo);

            BufferedReader br = new BufferedReader(fr);
            BufferedWriter bw = new BufferedWriter(fw);

            String tempval;

            while ((tempval = br.readLine()) != null) {
                if(tempval.contains("vn")){}
                else if(tempval.indexOf('v')==0){
                    tempval = tempval.trim();
                    String temparray1[] = tempval.split("  ");
                    Float floatvectors[] = new Float[temparray1.length];
                    String modifiedline= "";
                    for (int i = 1; i < temparray1.length; i++) {
                        floatvectors[i] = Float.parseFloat(temparray1[i]);
                        floatvectors[i] = floatvectors[i]*scale[i-1];
                        temparray1[i] = floatvectors[i].toString();
                    }

                    for (int i = 0; i < temparray1.length; i++) {
                        modifiedline = modifiedline + temparray1[i].toString() + " ";
                    }
                    bw.write(modifiedline); 
                    bw.newLine(); 
                }
                else {
                    bw.write(tempval); 
                    bw.newLine(); 
                }
            }
            System.out.println("output.obj created");
            fr.close();
			fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sc.close();
    }
}