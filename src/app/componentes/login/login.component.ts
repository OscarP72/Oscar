import { Component, ElementRef, isStandalone } from '@angular/core';
import { FormControl, FormsModule, Validators } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { Router } from '@angular/router';
import { catchError } from 'rxjs';
import { DocumentTypeService } from 'src/app/servicios/document-type.service';
import { LoginService } from 'src/app/servicios/login.service';
import { ModalLoginComponent } from '../modal-login/modal-login.component';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';

export interface DialogData {
  name: string;
  animal: string;
}
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',

})

export class LoginComponent {
  name: any;

  logado: boolean = false;
  inputUser: string = "";
  inputPassword: string = "";
  user: string = "";
  incorrectKey: boolean = false;//claveErronea
  keyMessage: String = "";//mensajeClave
  //counterAfkTimer: number = 30000;//contadortemporizador de inactividad
  //afkTimer: any;//temporizadorDeInactividad notActive incorrectKey keyMessage
  //notActive: boolean = false;//sin actividad
  hide = true;
  constructor(
    private dialog: MatDialog,
    private elementRef: ElementRef,
    private loginService: LoginService,
    private documentType: DocumentTypeService,
    private router: Router
  ) { }

  openDialog(): MatDialogRef<ModalLoginComponent> {
    return this.dialog.open(ModalLoginComponent, {
      data: { name: this.name },
    });


    // this.dialog.open(ModalLoginComponent,{
    //   width:'60%',
    //   height:'400px'
    // })
  }

  ngDoCheck() {
    if (sessionStorage.getItem('token') && !this.logado) {
      this.logado = true;
      this.user = sessionStorage.getItem('user') ?? '';
    }
    if (!this.logado && this.router.url !== "/home") {
      this.router.navigateByUrl("home");
    }
    // if (!this.notActive && this.logado) {
    //   clearTimeout(this.afkTimer);
    //   this.afkTimer = setTimeout(() => {
    //     console.log("Cerrado por inactividad!!");
    //     this.notActive = true;
    //     this.closeSession();
    //   }, this.counterAfkTimer);
    // }
  }
  logarse() {
    let dialogRef = this.openDialog();

    this.loginService.identificar("http://localhost:8080/login",
      this.inputUser, this.inputPassword)
      .pipe(
        catchError(error => {
          console.log(error);
          if (error.status === 0 || error.status === 404) {
            this.mensajeClaveErronea("No ha sido posible establecer la conexión. Intentelo más tarde");
          }
          dialogRef.close();
          return "";
        })
      )
      .subscribe((datos: any) => {
        console.log(datos)
        if (datos.token == null) {
          this.inputUser = "";
          this.inputPassword = "";
          this.elementRef.nativeElement.querySelector('#inputP').blur();
          this.elementRef.nativeElement.querySelector('#inputU').blur();
          this.mensajeClaveErronea("El Usuario o la Clave introducidos no son correctos");
        }
        if (datos.token != null) {
          console.log("acceso correcto");
          let userM = this.inputUser;
          let passM = this.inputPassword;
          sessionStorage.setItem('user', datos.user);
          console.log(userM + passM);
          sessionStorage.setItem('token', datos.token);
          //this.notActive = false;
          console.log(datos.token);
        }
        dialogRef.close();
      });
  }

  closeSession() {
    sessionStorage.removeItem('user');
    sessionStorage.removeItem('token');
    this.logado = false;
  }

  mensajeClaveErronea(mensaje: String) {
    this.incorrectKey = true;
    this.keyMessage = mensaje;
  }

  getDocumentsTypes() {
    const token = sessionStorage.getItem('token');
    console.log("Sacando documents types con token: " + token);
    this.documentType.getDatos("http://localhost:8080/once/documentsTypes")
      .subscribe({
        next: (response: any) => {
          console.log("status ok:" + response.status);
          console.log(response);
          response._embedded.fees.forEach((element: any) => {
            console.log(element.current);
          });
        },
        error: (error: any) => {
          console.log("status ko:" + error.status);
        }
      });
  }

  errorDeToken() {
    sessionStorage.setItem('token', 'error de token');
    console.log("token: " + sessionStorage.getItem('token'));
  }

}
// if (datos.token != null && datos.user != null && datos.roles != null && datos.roles.length > 0) {
//   console.log("Acceso correcto");

//   const userM = this.inputUsuario;
//   const passM = this.inputPassword;

//   sessionStorage.setItem('user', datos.user);
//   console.log(userM + passM);
//   sessionStorage.setItem('token', datos.token);
//   sessionStorage.setItem('rol', datos.roles[0].rol);

//   this.sinActividad = false;

//   const cont = this.elementRef.nativeElement.querySelector('.contenido');
//   if (cont) {
//     cont.innerHTML = "Bienvenido";
//     cont.classList.add('bg-success');
//   }

//   console.log(datos.token);

//   this.router.navigateByUrl("cuentas");

//   // Delay opcional (no está claro para qué se utiliza)
//   const delay = 1000;
// } else {
//   this.limpiarFormulario();
//   const inputP = this.elementRef.nativeElement.querySelector('#inputP');
//   const inputU = this.elementRef.nativeElement.querySelector('#inputU');
//   if (inputP && inputU) {
//     inputP.blur();
//     inputU.blur();
//   }
//   this.mensajeClaveErronea("El Usuario o la Clave introducidos no son correctos");
// }
