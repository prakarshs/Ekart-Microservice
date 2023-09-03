
<a name="readme-top"></a>


<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/othneildrew/Best-README-Template">
    <img src="images/logo.png" alt="Logo" width="80" height="80">
  </a>

  <h3 align="center">Ekart Microservices</h3>

  <p align="center">
    Introducing Ekart : A Complete Solo Microservices Project

</div>




<!-- ABOUT THE PROJECT -->
## About The Project
![product-screenshot]
Ekart is an innovative microservices project that transforms the world of online shopping. Our platform comprises four core services:

- Stock Services
- Order Services
- Payment Services
- Frontend Services
- User Service

## Microservices Architecture

We've adopted a "Database per Service" architecture to provide autonomy and scalability. Microservices are being adopted widely since they offer scalability, flexibility, and the ability to develop and deploy software components independently, which accelerates innovation and agility in modern software development.

## Key Features

- **Cloud API Gateway (SpringGateway)**: Efficiently routes requests between services, ensuring smooth communication.
- **Circuit Breaker (Resilience4j)**: Implemented in each service and the gateway for fault tolerance and reliability.
- **Okta Integration**: Industry-standard security is guaranteed through Okta integration in the gateway.

## Our Technology Stack

- **Service Discovery**: Eureka ensures smooth service discovery.
- **Centralized Configurations**: Config Server pulling from my github repo centralizes configurations.
- **Data Storage**: MySQL stores data reliably.
- **Traceability**: Docker and Zipkin Sleuth enhance traceability.
- **Frontend**: Our frontend is built with HTML, CSS, and JavaScript, offering a seamless shopping experience.
- **Communication**: RestTemplate, OpenFeign & WebClient in Cloud Gateway (since gateway is a WebFlux/Spring Reactive Web service) handle communication between services, making Ekart a comprehensive solution for modern commerce.


Ekart is not just about convenience; it's also about ensuring the utmost security, scalability, and fault tolerance. With Cloud API Gateway, circuit breakers, Okta integration, and a modern tech stack, we provide an unparalleled shopping experience.




## Built With

Our project's core lies in **Maven Spring Boot** and the FrontEnd is built on vanilla **HTML, CSS & JavaScript.** And the communication between the services is provided by Apache Kafka.

* ![SpringBoot]
* ![JavaScript]
* ![Okta]
* ![Apache]
* ![MySql]  


<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTACT -->
## Contact

Developed By - [Prakarsh Srivastava](https://www.linkedin.com/in/prakarsh2101/) - prakarsh2101@gmail.com

Project Link: [Ekart-Microservice](https://github.com/prakarshs/Ekart-Microservice)







<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/othneildrew/Best-README-Template.svg?style=for-the-badge
[contributors-url]: https://github.com/othneildrew/Best-README-Template/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/othneildrew/Best-README-Template.svg?style=for-the-badge
[forks-url]: https://github.com/othneildrew/Best-README-Template/network/members
[stars-shield]: https://img.shields.io/github/stars/othneildrew/Best-README-Template.svg?style=for-the-badge
[stars-url]: https://github.com/othneildrew/Best-README-Template/stargazers
[issues-shield]: https://img.shields.io/github/issues/othneildrew/Best-README-Template.svg?style=for-the-badge
[issues-url]: https://github.com/othneildrew/Best-README-Template/issues
[license-shield]: https://img.shields.io/github/license/othneildrew/Best-README-Template.svg?style=for-the-badge
[license-url]: https://github.com/othneildrew/Best-README-Template/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/othneildrew
[product-screenshot]: images/demo.gif
[SpringBoot]: https://img.shields.io/badge/SpringBoot-32CD32?style=for-the-badge&logo=springBoot&logoColor=white
[JavaScript]: https://img.shields.io/badge/JavaScript-FFEA00?style=for-the-badge&logo=javaScript&logoColor=black
[Docker]: https://img.shields.io/badge/Docker-0096FF?style=for-the-badge&logo=docker&logoColor=white
[Apache]: https://img.shields.io/badge/Apache-DE3163?style=for-the-badge&logo=apache&logoColor=white
[MySql]: https://img.shields.io/badge/MySql-F28C28?style=for-the-badge&logo=mysql&logoColor=white
[Next.js]: https://img.shields.io/badge/next.js-000000?style=for-the-badge&logo=nextdotjs&logoColor=white
[Next-url]: https://nextjs.org/
[React.js]: https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB
[React-url]: https://reactjs.org/
[Vue.js]: https://img.shields.io/badge/Vue.js-35495E?style=for-the-badge&logo=vuedotjs&logoColor=4FC08D
[Vue-url]: https://vuejs.org/
[Okta]: https://img.shields.io/badge/OKTA-00008b?style=for-the-badge&logo=okta&logoColor=white
[Angular.io]: https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white
[Angular-url]: https://angular.io/
[Svelte.dev]: https://img.shields.io/badge/Svelte-4A4A55?style=for-the-badge&logo=svelte&logoColor=FF3E00
[Svelte-url]: https://svelte.dev/
[Laravel.com]: https://img.shields.io/badge/Laravel-FF2D20?style=for-the-badge&logo=laravel&logoColor=white
[Laravel-url]: https://laravel.com
[Bootstrap.com]: https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white
[Bootstrap-url]: https://getbootstrap.com
[JQuery.com]: https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white
[JQuery-url]: https://jquery.com 
