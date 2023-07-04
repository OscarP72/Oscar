import { Component, EventEmitter, Output } from '@angular/core';
import ProfileBean from '../../beans/ProfileBean';
import { ProfileService } from 'src/app/servicios/profile.service';


@Component({
  selector: 'app-modal-profiles',
  templateUrl: './modal-profiles.component.html',
  styleUrls: ['./modal-profiles.component.css']
})

export class ModalProfilesComponent {
  id: string = "";
  image: string = '';
  firstname: string = "";
  lastname: string = "";
  identification: string = "";
  creditcard: string = "";
  email: string = "";
  city: string = "";
  country: string = "";
  phone: string = "";
  address: string = "";
  postalcode: string = "";

  user_id: string = '';
  documenttype: string = '';
  cardtype: string = '';
  user: string = '';

  username: string = '';
  password: string = '';

  message: string = "";

  imageContent: string = "";

  @Output() eventoProfile = new EventEmitter();


  constructor(private service: ProfileService) {}

  getImage(imageBytes: string | null): string {
    if (imageBytes) {
      this.image = imageBytes.toString()
      return 'data:image/jpeg;base64,' + imageBytes;
    }
    return 'assets/placeholder-image-profile.jpg';
  }

  selectImage() {
    const fileInput = document.querySelector('#file-input') as HTMLInputElement;
    if (fileInput) {
      fileInput.click();
    }
  }

  fileUpload(event: any) {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = (e) => {
        this.imageContent = reader.result as string;
        const base64Match = this.imageContent.match(/^data:image\/[a-z]+;base64,([\s\S]+)/i);

        if (base64Match && base64Match.length > 1) {
          this.imageContent = base64Match[1];
          const imgElement = document.querySelector('#setImage') as HTMLImageElement;
          if (imgElement) {
            imgElement.src = this.getImage(this.imageContent);
          }
        }
      };
      reader.readAsDataURL(file);
      file.close;
    }
  }

  saveData() {
    if(this.identification.trim()==''){
      this.message = 'Por favor, introduzca identificación.';
    } else if (this.creditcard.trim()==''){
      this.message = 'Por favor, introduzca el número de la tarjeta de credito.';
    } else if (this.email.trim()==''){
      this.message = 'Por favor, introduzca el correo electrónico.';
    } else if (this.phone.trim()==''){
      this.message = 'Por favor, introduzca el número de teléfono.';
    } else {

      if (this.imageContent) {
        this.image = this.imageContent.toString();
      }

      const profile = new ProfileBean(this.id, this.image || '', this.firstname, this.lastname, this.identification, this.creditcard, this.email, this.city, this.country, this.phone, this.address, this.postalcode, this.user, this.documenttype, this.cardtype);

      this.service.saveOrUpdate('http://localhost:8080/once/profiles/', profile)
        .subscribe((dato: boolean) => {
          if (dato) {
            this.message = '¡El perfil ha sido guardado correctamente!';            
          } else {
            this.message = 'Error al guardar el perfil.';
          }
        });
      }
  }

  openModal(id: string, data: any, action: string) {
    if (data !== '') {
      this.id = id;
      this.image = data.image;
      this.identification = data.identification;
      this.firstname = data.name;
      this.lastname = data.secondName;
      this.address = data.address;
      this.postalcode = data.postalCode;
      this.creditcard = data.creditCard;
      this.email = data.email;
      this.city = data.city;
      this.country = data.country;
      this.phone = data.phone;
      this.user = data._links.user.href;
      this.documenttype = data._links.documentTypes.href;
      this.cardtype = data._links.cardTypes.href;
    } else {
      this.clearAll();
    }

    if(action==='edit'){
      const userId = this.user.substring(this.user.lastIndexOf('/') + 1);

      this.service.getDatos("http://localhost:8080/once/users/"+userId)
      .subscribe({
        next: (rsp: any) => {
          this.username = rsp.user;
          this.password = rsp.password;

      },error: (error: any) => {
        console.error('Error al obtener los datos: ', error);
      }});
    }
    
    this.message = '';
  }

  closeModal() {
    this.eventoProfile.emit({ salida: "OK" });
    this.clearAll();
  }

  clearAll(){
    Object.assign(this, {
      id: '',
      image: '',
      identification: '',
      firstname: '',
      lastname: '',
      creditcard: '',
      address: '',
      postalcode: '',
      email: '',
      city: '',
      country: '',
      phone: '',
      user: '',
      documenttype: '',
      cardtype: '',
      username: '',
      password: ''
    });
  }
}
