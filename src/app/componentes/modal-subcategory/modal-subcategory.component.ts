import { Component, DoCheck, EventEmitter, Output } from '@angular/core';
import SubcategoryBean from '../../beans/SubcategoryBean';
import { SubcategoryService } from 'src/app/servicios/subcategories.service';

@Component({
  selector: 'app-modal-subcategory',
  templateUrl: './modal-subcategory.component.html',
  styleUrls: ['./modal-subcategory.component.css']
})
export class ModalSubcategoryComponent implements DoCheck {
  id: number = 0
  descripcion: string
  mensaje: string = "";
  fin: boolean = false
  subcategory: string = ""
  subcategoryPlaceHolder: string = ""
  subcategory_id: string = ""
  categoria: string = "0"
  categorias: Array<any>
  @Output() eventoAComunicar = new EventEmitter();
  antiguoId: number = this.id
  subtitulo: string = ""



  constructor(private service: SubcategoryService) {
    this.descripcion = "";
    this.subcategory_id = "";
    this.categorias = []
  }
  ngOnInit(): void {
    this.service.getDatos("http://localhost:8080/once/categories")
      .subscribe((datos: any) => {
        this.categorias = datos._embedded.categories
      });
  }
  ngDoCheck(): void {

    if (this.id === 0) {
      this.subtitulo = "ALTA"
      this.categoria = "0"
    } else {
      this.subtitulo = "MODIFICACION"
    }

    if (this.id !== this.antiguoId && this.id > 0) {
      this.antiguoId = this.id

      console.log("id entrada: " + this.id);
      this.service.getDatos("http://localhost:8080/once/subcategories/" + this.id)
        .subscribe((datos: any) => {
          this.categoria = datos._links.category.href.substring(datos._links.category.href.lastIndexOf('/') + 1);
          this.subcategoryPlaceHolder = datos.description;
          console.log("categoria " + this.categoria)
        });

    }
  }

  realizarComunicacion() {
    this.id = 0;
    this.subcategory = "";
    this.subcategoryPlaceHolder = "";
    this.fin = false;
    this.eventoAComunicar.emit({ salida: "OK" });
    this.mensaje ="";
  }

  grabar() {
    this.fin = false;
    if (this.descripcion.trim() !== "") {
      this.service.saveOrUpdate("http://localhost:8080/once/subcategories",
        new SubcategoryBean(this.id, this.descripcion, "http://localhost:8080/once/categories/" + this.categoria))
        .subscribe((dato: boolean) => {
          if (dato) {
            this.mensaje = "Grabación realizada correctamente";
            this.subcategoryPlaceHolder = "";
          } else {
            this.mensaje = "La grabación no se ha realizado";
          }
        });
    } else {
      this.mensaje = "Debes introducir un valor";
    }
  }
}