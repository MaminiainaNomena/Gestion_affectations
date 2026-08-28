<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Employé - Gestion des affectations</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css?v=3">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/layout.css?v=3">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components.css?v=3">
  </head>
  <body>
    <%@ include file="notification.jspf" %>

    <header class="top-header">
      <%@ include file="page-select.jspf" %>
    </header>

    <div class="container">
      <main class="main-panel">
        <section class="page-heading">
          <div>
            <h1>${empty employe ? 'Nouvel employé' : 'Modifier employé'}</h1>
            <p class="text-muted">Renseignez les informations professionnelles du collaborateur.</p>
          </div>
        </section>

        <section class="panel form-panel">
          <form method="post" action="${pageContext.request.contextPath}/employes">
            <input type="hidden" name="oldCode" value="${employe.codeemp}">

            <div class="form-grid">
              <label class="field">
                Code employé
                <input name="codeemp" maxlength="20" required value="${employe.codeemp}">
              </label>

              <label class="field">
                Nom
                <input name="nom" maxlength="80" required value="${employe.nom}">
              </label>

              <label class="field">
                Prénom
                <input name="prenom" maxlength="80" required value="${employe.prenom}">
              </label>

              <label class="field">
                Poste
                <input name="poste" maxlength="100" required value="${employe.poste}">
              </label>
            </div>

            <div class="form-actions">
              <button class="btn btn-primary" type="submit">Enregistrer</button>
              <a class="btn btn-outline" href="${pageContext.request.contextPath}/employes">Annuler</a>
            </div>
          </form>
        </section>
      </main>
    </div>
  </body>
</html>