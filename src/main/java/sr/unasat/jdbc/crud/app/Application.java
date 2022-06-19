package sr.unasat.jdbc.crud.app;

import sr.unasat.jdbc.crud.entities.FysiekeDetails;
import sr.unasat.jdbc.crud.entities.Persoon;
import sr.unasat.jdbc.crud.repositories.FysiekeDetailsRepository;
import sr.unasat.jdbc.crud.repositories.PersoonRepository;

public class Application {

    public static void main(String[] args) {
        printAppHeader();
        FysiekeDetailsRepository detailsRepo = new FysiekeDetailsRepository();
        PersoonRepository persoonRepo = new PersoonRepository();

        System.out.println("All records found: \n" + detailsRepo.findAllRecords() + "\n");

        Persoon person1 = new Persoon("Wicki");
        int person1Id = persoonRepo.insertOneRecord(person1);
        person1.setId(person1Id);

        FysiekeDetails person1Details = new FysiekeDetails(170, 65, "vrouw", person1);
        System.out.println("Inserted record with id: " + detailsRepo.insertOneRecord(person1Details) + "\n");

        System.out.println("Persoon with id " + person1.getId() + ": " + detailsRepo.findOneRecord(person1) + "\n");


        person1Details.setGewicht(70);
        detailsRepo.updateOneRecord(person1Details);

        person1Details = detailsRepo.findOneRecord(person1Details.getPersoon());
        System.out.println("Updated record with id:" + person1Details.getId() + "\n");
        System.out.println(person1Details + "\n");

        detailsRepo.deleteOneRecord(person1Details);
        System.out.println("Deleted record with id:" + person1Details.getId() + "\n");
    }

    private static void printAppHeader() {
        System.out.println("\n" + "\n" +
                "\"───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────\\n\" +\n" +
                "\"─██████████████─████████████───████████████████───██████████████─██████████████────██████████████───██████████████─██████████████─██████──████████─\\n\" +\n" +
                "\"─██░░░░░░░░░░██─██░░░░░░░░████─██░░░░░░░░░░░░██───██░░░░░░░░░░██─██░░░░░░░░░░██────██░░░░░░░░░░██───██░░░░░░░░░░██─██░░░░░░░░░░██─██░░██──██░░░░██─\\n\" +\n" +
                "\"─██░░██████░░██─██░░████░░░░██─██░░████████░░██───██░░██████████─██░░██████████────██░░██████░░██───██░░██████░░██─██░░██████████─██░░██──██░░████─\\n\" +\n" +
                "\"─██░░██──██░░██─██░░██──██░░██─██░░██────██░░██───██░░██─────────██░░██────────────██░░██──██░░██───██░░██──██░░██─██░░██─────────██░░██──██░░██───\\n\" +\n" +
                "\"─██░░██████░░██─██░░██──██░░██─██░░████████░░██───██░░██████████─██░░██████████────██░░██████░░████─██░░██──██░░██─██░░██████████─██░░██████░░██───\\n\" +\n" +
                "\"─██░░░░░░░░░░██─██░░██──██░░██─██░░░░░░░░░░░░██───██░░░░░░░░░░██─██░░░░░░░░░░██────██░░░░░░░░░░░░██─██░░██──██░░██─██░░░░░░░░░░██─██░░░░░░░░░░██───\\n\" +\n" +
                "\"─██░░██████░░██─██░░██──██░░██─██░░██████░░████───██░░██████████─██████████░░██────██░░████████░░██─██░░██──██░░██─██░░██████████─██░░██████░░██───\\n\" +\n" +
                "\"─██░░██──██░░██─██░░██──██░░██─██░░██──██░░██─────██░░██─────────────────██░░██────██░░██────██░░██─██░░██──██░░██─██░░██─────────██░░██──██░░██───\\n\" +\n" +
                "\"─██░░██──██░░██─██░░████░░░░██─██░░██──██░░██████─██░░██████████─██████████░░██────██░░████████░░██─██░░██████░░██─██░░██████████─██░░██──██░░████─\\n\" +\n" +
                "\"─██░░██──██░░██─██░░░░░░░░████─██░░██──██░░░░░░██─██░░░░░░░░░░██─██░░░░░░░░░░██────██░░░░░░░░░░░░██─██░░░░░░░░░░██─██░░░░░░░░░░██─██░░██──██░░░░██─\\n\" +\n" +
                "\"─██████──██████─████████████───██████──██████████─██████████████─██████████████────████████████████─██████████████─██████████████─██████──████████─\\n\" +\n" +
                "\"───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────\");\n" +
                "\n" +
                "\n"
                + "Project Authors:\n Kimbelie Chotkan\n Zakur Lie-A-Ling \n Joel Naarenodorp \n Rouche Ronosemito \n Chagally Schenkers\n Shared Sewruttan \n ");
    }
}