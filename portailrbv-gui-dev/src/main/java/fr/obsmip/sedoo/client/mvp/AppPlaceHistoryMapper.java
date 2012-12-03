package fr.obsmip.sedoo.client.mvp;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

import fr.obsmip.sedoo.client.place.DrainageBasinChoicePlace;
import fr.obsmip.sedoo.client.place.DrainageBasinEditingPlace;
import fr.obsmip.sedoo.client.place.LoginPlace;
import fr.obsmip.sedoo.client.place.MetadataDisplayPlace;
import fr.obsmip.sedoo.client.place.MetadataEditingPlace;
import fr.obsmip.sedoo.client.place.MetadataListPlace;
import fr.obsmip.sedoo.client.place.MetadataSearchPlace;
import fr.obsmip.sedoo.client.place.ObservatoryContactEditingPlace;
import fr.obsmip.sedoo.client.place.ObservatoryEditingPlace;
import fr.obsmip.sedoo.client.place.ObservatoryManagementPlace;
import fr.obsmip.sedoo.client.place.SwitchLanguagePlace;
import fr.obsmip.sedoo.client.place.SystemPlace;
import fr.obsmip.sedoo.client.place.WelcomePlace;

/**
 * PlaceHistoryMapper interface is used to attach all places which the
 * PlaceHistoryHandler should be aware of. This is done via the @WithTokenizers
 * annotation or by extending PlaceHistoryMapperWithFactory and creating a
 * separate TokenizerFactory.
 */
@WithTokenizers( {  DrainageBasinChoicePlace.Tokenizer.class, SystemPlace.Tokenizer.class,  DrainageBasinEditingPlace.Tokenizer.class, WelcomePlace.Tokenizer.class, MetadataEditingPlace.Tokenizer.class,MetadataDisplayPlace.Tokenizer.class, SwitchLanguagePlace.Tokenizer.class, MetadataListPlace.Tokenizer.class, MetadataSearchPlace.Tokenizer.class, LoginPlace.Tokenizer.class, ObservatoryManagementPlace.Tokenizer.class, ObservatoryContactEditingPlace.Tokenizer.class, ObservatoryEditingPlace.Tokenizer.class})
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {
}
