package nl.rijksoverheid.rdw.rde.serverdemo.components;

import nl.rijksoverheid.rdw.rde.serverdemo.commands.EnrollDocumentArgs;
import nl.rijksoverheid.rdw.rde.serverdemo.repositories.MessageMetadata;
import nl.rijksoverheid.rdw.rde.serverlib.EnrollDocumentDto;
import nl.rijksoverheid.rdw.rde.serverlib.MessageInfoDto;

public class Mapper {

//    public static RegistrationUserArgs Map(UserRegistrationDto dto)
//    {
//        return new RegistrationUserArgs(
//                dto.getUserName(),
//                dto.getEmail(),
//                dto.getPassword()
//        );
//    }

    public static EnrollDocumentArgs Map(EnrollDocumentDto value)
    {
        var result = new EnrollDocumentArgs();

        result.setDisplayName(value.getDisplayName());

        result.setShortFileId(value.getShortFileId());
        result.setFileContents(value.getFileContents());

        result.setFileReadLength(value.getFileReadLength());
        result.setEncryptedCommand(value.getEncryptedCommand());

        result.setRbResponse(value.getRbResponse());

        result.setPcdPublicKey(value.getPcdPublicKey());
        result.setPcdPrivateKey(value.getPcdPrivateKey());
        result.setDataGroup14(value.getDataGroup14());

        return result;
    }

    public static MessageInfoDto Map(MessageMetadata item, UrlProvider urlProvider) {
        var result = new MessageInfoDto();
        result.setUrl(urlProvider.getMessageUrl(item.getId()));
        result.setId(item.getId());
        result.setWhenSent(item.getWhenSent().toString()); //TODO format?
        result.setFrom(item.getFromUser());
        result.setNote(item.getNote());
        result.setTo(item.getToUser());
        return result;
    }
}
