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

        System.out.println("All records found: \n" + detailsRepo.findAllRecords());

        Persoon person1 = new Persoon("Kimbelie");
        int person1Id = persoonRepo.insertOneRecord(person1);
        person1.setId(person1Id);

        FysiekeDetails person1Details = new FysiekeDetails(170, 65, "vrouw", person1);
        System.out.println("record inserted with id: " + detailsRepo.insertOneRecord(person1Details));

        System.out.println("record with persoon id " + person1.getId() + ": " + detailsRepo.findOneRecord(person1));


        person1Details.setGewicht(70);
        detailsRepo.updateOneRecord(person1Details);

        System.out.println("Update record with id:" + person1Details.getId());
        person1Details = detailsRepo.findOneRecord(person1Details.getPersoon());
        System.out.println(person1Details);

        System.out.println("Delete record with id:" + person1Details.getId());
        detailsRepo.deleteOneRecord(person1Details);
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