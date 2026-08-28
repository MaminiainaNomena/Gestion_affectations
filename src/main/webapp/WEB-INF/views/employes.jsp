<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Employés - Gestion des affectations</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css?v=3">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/layout.css?v=3">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components.css?v=3">
</head>
<body>
<%@ include file="notification.jspf" %>
<header class="top-header">
  <%@ include file="page-select.jspf" %>
  <form action="${pageContext.request.contextPath}/employes" method="get" class="search-form">
    <div class="search-wrapper">
      <input type="text" name="q" value="${param.q}" placeholder="Rechercher..." class="search-input" aria-label="Rechercher un employé">
      <button type="submit" class="btn-search" title="Chercher"><svg class="icon icon-md" viewBox="0 0 24 24"><circle cx="11" cy="11" r="8"></circle><line x1="21" y1="21" x2="16.65" y2="16.65"></line></svg></button>
    </div>
  </form>
  <a class="btn btn-primary" href="${pageContext.request.contextPath}/employes/new"><svg class="icon icon-sm" viewBox="0 0 24 24"><line x1="12" y1="5" x2="12" y2="19"></line><line x1="5" y1="12" x2="19" y2="12"></line></svg> CRÉER</a>
</header>
<div class="container"><main class="main-panel"><section class="panel"><div class="table-wrap"><table class="table-crud employee-table"><thead><tr><th>Code</th><th>Nom</th><th>Prénom</th><th>Poste</th></tr></thead><tbody><c:forEach var="e" items="${employes}"><tr><td><strong>${e.codeemp}</strong></td><td>${e.nom}</td><td>${e.prenom}</td><td class="post-cell">${e.poste}<span class="row-actions"><a class="icon-action icon-edit" data-tooltip="Modifier l'employé" title="Modifier l'employé" aria-label="Modifier l'employé" href="${pageContext.request.contextPath}/employes/edit?id=${e.codeemp}"><svg class="icon icon-sm" viewBox="0 0 24 24"><path d="M12 20h9"></path><path d="M16.5 3.5a2.1 2.1 0 0 1 3 3L8 18l-4 1 1-4Z"></path></svg></a><a class="icon-action icon-delete" data-tooltip="Supprimer l'employé" title="Supprimer l'employé" aria-label="Supprimer l'employé" onclick="return confirm('Supprimer cet employé ?')" href="${pageContext.request.contextPath}/employes/delete?id=${e.codeemp}"><svg class="icon icon-sm" viewBox="0 0 24 24"><path d="M3 6h18"></path><path d="M8 6V4h8v2"></path><path d="M19 6l-1 15H6L5 6"></path><path d="M10 11v6"></path><path d="M14 11v6"></path></svg></a></span></td></tr></c:forEach></tbody></table></div></section></main></div>
</body>
</html>
