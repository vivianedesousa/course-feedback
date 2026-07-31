package br.com.coursefeedback.notification.dto;
import br.com.coursefeedback.notification.domain.Urgency;

///Responsabilidade:
    //Receber evento do SNS.
//Ler o NotificationMessageDTO.
//Enviar o e-mail (ou outra notificação).
//Recebe mensagens do SNS quando um feedback crítico é enviado.
//// OBS ABAIXO
// // Esse DTO será convertido para JSON e enviado ao SNS.
// sua responsabilidde e transport os dados para sns
    //Finalidade do NotificationMessageDTO: transportar os dados que serão enviados pelo SNS.
    //qual a finalidade desse DTO  e trandportar dados  que serao enviado ao sns
    public class NotificationMessageDTO {

        private String feedbackId;
        private String courseId;
        private String description;
        private Integer rating;
        private Urgency urgency;
        private String submittedAt;

        public NotificationMessageDTO() {
        }

        public String getFeedbackId() {
            return feedbackId;
        }

        public void setFeedbackId(String feedbackId) {
            this.feedbackId = feedbackId;
        }

        public String getCourseId() {
            return courseId;
        }

        public void setCourseId(String courseId) {
            this.courseId = courseId;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Integer getRating() {
            return rating;
        }

        public void setRating(Integer rating) {
            this.rating = rating;
        }

        public Urgency getUrgency() {
            return urgency;
        }

        public void setUrgency(Urgency urgency) {
            this.urgency = urgency;
        }

        public String getSubmittedAt() {
            return submittedAt;
        }

        public void setSubmittedAt(String submittedAt) {
            this.submittedAt = submittedAt;
        }
    }

