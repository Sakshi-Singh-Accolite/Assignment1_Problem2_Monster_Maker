import java.lang.reflect.Field;
import java.util.Objects;
import java.util.Scanner;

public class Monster {
    private String colourOfEye;
    private String featherColour;
    private boolean magicalAbility;
    private int sizeOfTheMonster;
    private int strength;
    private int durability;
    private String weakness;
    private int aggressionLevel;

    public Monster(String colourOfEye, String featherColour, boolean magicalAbility,
                   int sizeOfTheMonster, int strength, int durability, String weakness,
                   int aggressionLevel){
        this.colourOfEye = colourOfEye;
        this.featherColour = featherColour;
        this.magicalAbility = magicalAbility;
        this.sizeOfTheMonster = sizeOfTheMonster;
        this.strength = strength;
        this.durability = durability;
        this.weakness = weakness;
        this.aggressionLevel = aggressionLevel;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "colourOfEye='" + colourOfEye + '\'' +
                ", featherColour='" + featherColour + '\'' +
                ", magicalAbility=" + magicalAbility +
                ", sizeOfTheMonster=" + sizeOfTheMonster +
                ", strength=" + strength +
                ", durability=" + durability +
                ", weakness='" + weakness + '\'' +
                ", aggressionLevel=" + aggressionLevel +
                '}';
    }

    public static Monster breedMonster(Monster parent1, Monster parent2) throws Exception{
        Field[] fields=Monster.class.getDeclaredFields();

        Monster baby = new Monster("","",false,0,0,
                0,"", 0);
        for(Field field:fields){
            field.setAccessible(true);
            Object value1 = field.get(parent1);
            Object value2 = field.get(parent2);

            if(value1 instanceof String){
                if(!value1.equals(value2)){
                    String newValue = (Math.random()>0.5)?(String) value1: (String) value2;
                    field.set(baby, newValue);
                }
                else{
                    field.set(baby, value1);
                }
            }
            else if(value1 instanceof Boolean){
                boolean newValue=(Math.random() > 0.5 ? (boolean) value1 : (boolean) value2);
                field.set(baby, newValue);
            }
            else if (value1 instanceof Integer) {
                int newValue = (int) Math.floor((double) ((Integer) value1 + (Integer) value2) / 2);
                field.set(baby,newValue);
            }
        }


        return baby;
    }
    public static Monster createMoster() throws Exception{
        Scanner scanner=new Scanner(System.in);

        System.out.print("Enter Monster eye color: ");
        String colourOfEye = scanner.nextLine();

        System.out.print("Enter Monster feather color: ");
        String featherColour = scanner.nextLine();

        System.out.print("Enter Monster magical ability(true/false): ");
        boolean magicalAbilities = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Enter Monster size: ");
        int sizeOfTheMonster = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Monster strength: ");
        int strength = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Monster durability: ");
        int durability = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Monster weakness(weak/moderate/strong): ");
        String weakness = scanner.nextLine();

        System.out.print("Enter Monster aggression level: ");
        int aggressionLevel = Integer.parseInt(scanner.nextLine());

        return new Monster(colourOfEye,featherColour,magicalAbilities,
                sizeOfTheMonster,strength,durability,weakness,aggressionLevel);
    }


    //main method
    public static void main(String[] args) throws Exception {
        System.out.println("Enter details of parent 1 ");
        Monster parent1 = createMoster();
        System.out.println("Enter details of parent 2 ");
        Monster parent2 = createMoster();

        Monster baby=breedMonster(parent1,parent2);

        System.out.println("Parent 1 : "+ parent1);
        System.out.println("Parent 2 : "+ parent2);
        System.out.println("Baby : "+baby);
    }
}