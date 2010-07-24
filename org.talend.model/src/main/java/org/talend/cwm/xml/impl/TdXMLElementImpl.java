/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.cwm.xml.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.talend.cwm.helper.TaggedValueHelper;
import org.talend.cwm.xml.TdXMLContent;
import org.talend.cwm.xml.TdXMLDocument;
import org.talend.cwm.xml.TdXMLElement;
import org.talend.cwm.xml.XmlPackage;
import orgomg.cwm.objectmodel.core.TaggedValue;
import orgomg.cwm.resource.xml.impl.ElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Td XML Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.cwm.xml.impl.TdXMLElementImpl#getXsdElementDeclaration <em>Xsd Element Declaration</em>}</li>
 *   <li>{@link org.talend.cwm.xml.impl.TdXMLElementImpl#getOwnedDocument <em>Owned Document</em>}</li>
 *   <li>{@link org.talend.cwm.xml.impl.TdXMLElementImpl#getJavaType <em>Java Type</em>}</li>
 *   <li>{@link org.talend.cwm.xml.impl.TdXMLElementImpl#getXmlContent <em>Xml Content</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TdXMLElementImpl extends ElementImpl implements TdXMLElement {

    /**
     * The cached value of the '{@link #getXsdElementDeclaration() <em>Xsd Element Declaration</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getXsdElementDeclaration()
     * @generated
     * @ordered
     */
    protected EObject xsdElementDeclaration;

    /**
     * The cached value of the '{@link #getOwnedDocument() <em>Owned Document</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOwnedDocument()
     * @generated
     * @ordered
     */
    protected TdXMLDocument ownedDocument;

    /**
     * The default value of the '{@link #getJavaType() <em>Java Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getJavaType()
     * @generated
     * @ordered
     */
    protected static final String JAVA_TYPE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getJavaType() <em>Java Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getJavaType()
     * @generated
     * @ordered
     */
    protected String javaType = JAVA_TYPE_EDEFAULT;

    /**
     * The cached value of the '{@link #getXmlContent() <em>Xml Content</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getXmlContent()
     * @generated
     * @ordered
     */
    protected TdXMLContent xmlContent;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TdXMLElementImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return XmlPackage.Literals.TD_XML_ELEMENT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EObject getXsdElementDeclaration() {
        if (xsdElementDeclaration != null && xsdElementDeclaration.eIsProxy()) {
            InternalEObject oldXsdElementDeclaration = (InternalEObject) xsdElementDeclaration;
            xsdElementDeclaration = eResolveProxy(oldXsdElementDeclaration);
            if (xsdElementDeclaration != oldXsdElementDeclaration) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, XmlPackage.TD_XML_ELEMENT__XSD_ELEMENT_DECLARATION,
                            oldXsdElementDeclaration, xsdElementDeclaration));
            }
        }
        return xsdElementDeclaration;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EObject basicGetXsdElementDeclaration() {
        return xsdElementDeclaration;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setXsdElementDeclaration(EObject newXsdElementDeclaration) {
        EObject oldXsdElementDeclaration = xsdElementDeclaration;
        xsdElementDeclaration = newXsdElementDeclaration;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, XmlPackage.TD_XML_ELEMENT__XSD_ELEMENT_DECLARATION,
                    oldXsdElementDeclaration, xsdElementDeclaration));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TdXMLDocument getOwnedDocument() {
        if (ownedDocument != null && ownedDocument.eIsProxy()) {
            InternalEObject oldOwnedDocument = (InternalEObject) ownedDocument;
            ownedDocument = (TdXMLDocument) eResolveProxy(oldOwnedDocument);
            if (ownedDocument != oldOwnedDocument) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, XmlPackage.TD_XML_ELEMENT__OWNED_DOCUMENT,
                            oldOwnedDocument, ownedDocument));
            }
        }
        return ownedDocument;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TdXMLDocument basicGetOwnedDocument() {
        return ownedDocument;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOwnedDocument(TdXMLDocument newOwnedDocument) {
        TdXMLDocument oldOwnedDocument = ownedDocument;
        ownedDocument = newOwnedDocument;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, XmlPackage.TD_XML_ELEMENT__OWNED_DOCUMENT, oldOwnedDocument,
                    ownedDocument));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getJavaType() {
        return javaType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setJavaType(String newJavaType) {
        String oldJavaType = javaType;
        javaType = newJavaType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, XmlPackage.TD_XML_ELEMENT__JAVA_TYPE, oldJavaType, javaType));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TdXMLContent getXmlContent() {
        if (xmlContent != null && xmlContent.eIsProxy()) {
            InternalEObject oldXmlContent = (InternalEObject) xmlContent;
            xmlContent = (TdXMLContent) eResolveProxy(oldXmlContent);
            if (xmlContent != oldXmlContent) {
                InternalEObject newXmlContent = (InternalEObject) xmlContent;
                NotificationChain msgs = oldXmlContent.eInverseRemove(this, EOPPOSITE_FEATURE_BASE
                        - XmlPackage.TD_XML_ELEMENT__XML_CONTENT, null, null);
                if (newXmlContent.eInternalContainer() == null) {
                    msgs = newXmlContent.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XmlPackage.TD_XML_ELEMENT__XML_CONTENT, null,
                            msgs);
                }
                if (msgs != null)
                    msgs.dispatch();
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, XmlPackage.TD_XML_ELEMENT__XML_CONTENT,
                            oldXmlContent, xmlContent));
            }
        }
        return xmlContent;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TdXMLContent basicGetXmlContent() {
        return xmlContent;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetXmlContent(TdXMLContent newXmlContent, NotificationChain msgs) {
        TdXMLContent oldXmlContent = xmlContent;
        xmlContent = newXmlContent;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    XmlPackage.TD_XML_ELEMENT__XML_CONTENT, oldXmlContent, newXmlContent);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setXmlContent(TdXMLContent newXmlContent) {
        if (newXmlContent != xmlContent) {
            NotificationChain msgs = null;
            if (xmlContent != null)
                msgs = ((InternalEObject) xmlContent).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
                        - XmlPackage.TD_XML_ELEMENT__XML_CONTENT, null, msgs);
            if (newXmlContent != null)
                msgs = ((InternalEObject) newXmlContent).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
                        - XmlPackage.TD_XML_ELEMENT__XML_CONTENT, null, msgs);
            msgs = basicSetXmlContent(newXmlContent, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, XmlPackage.TD_XML_ELEMENT__XML_CONTENT, newXmlContent,
                    newXmlContent));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public void setContentType(String contentType) {
        TaggedValueHelper.setTaggedValue(this, TaggedValueHelper.DATA_CONTENT_TYPE_TAGGED_VAL, contentType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public String getContentType() {
        TaggedValue tv = TaggedValueHelper.getTaggedValue(TaggedValueHelper.DATA_CONTENT_TYPE_TAGGED_VAL, this.getTaggedValue());
        if (tv == null) {
            return "";
        }
        return tv.getValue();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case XmlPackage.TD_XML_ELEMENT__XML_CONTENT:
            return basicSetXmlContent(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case XmlPackage.TD_XML_ELEMENT__XSD_ELEMENT_DECLARATION:
            if (resolve)
                return getXsdElementDeclaration();
            return basicGetXsdElementDeclaration();
        case XmlPackage.TD_XML_ELEMENT__OWNED_DOCUMENT:
            if (resolve)
                return getOwnedDocument();
            return basicGetOwnedDocument();
        case XmlPackage.TD_XML_ELEMENT__JAVA_TYPE:
            return getJavaType();
        case XmlPackage.TD_XML_ELEMENT__XML_CONTENT:
            if (resolve)
                return getXmlContent();
            return basicGetXmlContent();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case XmlPackage.TD_XML_ELEMENT__XSD_ELEMENT_DECLARATION:
            setXsdElementDeclaration((EObject) newValue);
            return;
        case XmlPackage.TD_XML_ELEMENT__OWNED_DOCUMENT:
            setOwnedDocument((TdXMLDocument) newValue);
            return;
        case XmlPackage.TD_XML_ELEMENT__JAVA_TYPE:
            setJavaType((String) newValue);
            return;
        case XmlPackage.TD_XML_ELEMENT__XML_CONTENT:
            setXmlContent((TdXMLContent) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
        case XmlPackage.TD_XML_ELEMENT__XSD_ELEMENT_DECLARATION:
            setXsdElementDeclaration((EObject) null);
            return;
        case XmlPackage.TD_XML_ELEMENT__OWNED_DOCUMENT:
            setOwnedDocument((TdXMLDocument) null);
            return;
        case XmlPackage.TD_XML_ELEMENT__JAVA_TYPE:
            setJavaType(JAVA_TYPE_EDEFAULT);
            return;
        case XmlPackage.TD_XML_ELEMENT__XML_CONTENT:
            setXmlContent((TdXMLContent) null);
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
        case XmlPackage.TD_XML_ELEMENT__XSD_ELEMENT_DECLARATION:
            return xsdElementDeclaration != null;
        case XmlPackage.TD_XML_ELEMENT__OWNED_DOCUMENT:
            return ownedDocument != null;
        case XmlPackage.TD_XML_ELEMENT__JAVA_TYPE:
            return JAVA_TYPE_EDEFAULT == null ? javaType != null : !JAVA_TYPE_EDEFAULT.equals(javaType);
        case XmlPackage.TD_XML_ELEMENT__XML_CONTENT:
            return xmlContent != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (javaType: ");
        result.append(javaType);
        result.append(')');
        return result.toString();
    }

} //TdXMLElementImpl
