package controllers;

import static play.data.Form.form;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import models.Patient;


import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import scala.concurrent.stm.Ref.View;
import views.html.index;
import views.html.helper.form;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;
import com.avaje.ebean.RawSql;
import com.avaje.ebean.RawSqlBuilder;

public class ReceptionCon extends Controller {
 
	static ResourceBundle bundle = ResourceBundle.getBundle("bundle.Texts");

	private static Form<Patient> patientForm = Form.form(Patient.class);

	public static Result index() {
		if (Patient.find.all() == null) {
			return ok(index.render("no record found!"));
		} else {
			return ok(views.html.reception.patient_list.render(Patient.find
					.all(), bundle));
		}
	}

	public static Result searchPatient() {
		DynamicForm form = form().bindFromRequest();

		String type = form.get("type");

		String key = form.get("key");

		List<Patient> list = new ArrayList<Patient>();

		if (type == null || key == null){
			list = Patient.find.all();
		} else {
			list = Patient.find.where().like(type, "%" + key + "%").findList();
		}

		return ok(views.html.reception.patient_search.render(list, type, key, bundle));
	}

	public static Result updatePatient(Integer id) {
		Form<Patient> filledForm = form(Patient.class).fill(
				Patient.find.byId(id));
		return ok(views.html.reception.editForm.render(filledForm, bundle));

	}

	 public static Result saveUpdatePatient() {
	 Form<Patient> filledForm = form(Patient.class).bindFromRequest();
	 if (filledForm.hasErrors()) {
	 return badRequest(views.html.reception.editForm.render( filledForm, bundle));
	 } else {
	 filledForm.get().update();
	 return redirect(routes.ReceptionCon.index());
	 }
	 }

	public static Result createPatient() {
		Patient p = new Patient();
		List<Object> ids = Patient.find.findIds();

		int max = 0;
		for (Object id : ids)
			max = Math.max((Integer) id, max);

		max = max + 1;

		p.id = max;

		patientForm = patientForm.fill(p);

		System.out.println(patientForm);

		return ok(views.html.reception.newForm.render(patientForm, bundle));
	}

	public static Result saveCreatePatient() {
		Form<Patient> filledForm = form(Patient.class).bindFromRequest();
		if (filledForm.hasErrors()) {
			return badRequest(views.html.reception.newForm.render(filledForm, bundle));
		} else {
			
			filledForm.get().save();
			return redirect(routes.ReceptionCon.index());
		}
	}

	public static Result printListPatient() {

		return ok(views.html.reception.print_list.render(Patient.find.all(), bundle));

	}

	public static Result printRecieptPatient(Integer id) {
		return ok(views.html.reception.print_reciept.render(Patient.find.byId(id), bundle));

	}

	public static Result deletePatient(Integer id) {
		Patient.find.ref(id).delete();
		return redirect(routes.ReceptionCon.index());
	}
}
