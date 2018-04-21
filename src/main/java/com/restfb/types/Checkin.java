/**
 * Copyright (c) 2010-2018 Mark Allen, Norbert Bartels.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.restfb.types;

import static com.restfb.util.DateUtils.toDateFromLongFormat;
import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.restfb.Facebook;
import com.restfb.JsonMapper;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents the <a href="http://developers.facebook.com/docs/reference/api/checkin">Checkin Graph API type</a>.
 * 
 * @author <a href="http://restfb.com">Mark Allen</a>
 * @since 1.6
 */
public class Checkin extends FacebookType {

  /**
   * The message the user added to the check-in.
   * 
   * @return The message the user added to the check-in.
   */
  @Getter
  @Setter
  @Facebook
  private String message;

  /**
   * The ID and name of the user who made the check-in.
   * 
   * @return The ID and name of the user who made the check-in.
   */
  @Getter
  @Setter
  @Facebook
  private NamedFacebookType from;

  /**
   * The ID and name of the application that made the check-in.
   * 
   * @return The ID and name of the application that made the check-in.
   */
  @Getter
  @Setter
  @Facebook
  private NamedFacebookType application;

  /**
   * The ID, name, and location of the Facebook Page that represents the location of the check-in.
   * 
   * @return The ID, name, and location of the Facebook Page that represents the location of the check-in.
   */
  @Getter
  @Setter
  @Facebook
  private Place place;

  @Facebook("created_time")
  private transient String rawCreatedTime;

  /**
   * The time the check-in was created.
   * 
   * @return The time the check-in was created.
   */
  @Getter
  @Setter
  private Date createdTime;

  @Facebook
  private List<Comment> comments = new ArrayList<Comment>();

  @Facebook
  private List<NamedFacebookType> tags = new ArrayList<NamedFacebookType>();

  private static final long serialVersionUID = 2L;

  @JsonMapper.JsonMappingCompleted
  void convertTime() {
    createdTime = toDateFromLongFormat(rawCreatedTime);
  }

  /**
   * The comments for the check-in.
   * 
   * @return The comments for the check-in.
   */
  public List<Comment> getComments() {
    return unmodifiableList(comments);
  }

  public boolean addComment(Comment comment) {
    return comments.add(comment);
  }

  public boolean removeComment(Comment comment) {
    return comments.remove(comment);
  }

  /**
   * Tags for the check-in. I.e. Users tagged in the check-in
   * 
   * @return Tags for the check-in.
   */
  public List<NamedFacebookType> getTags() {
    return unmodifiableList(tags);
  }

  public boolean addTag(NamedFacebookType tag) {
    return tags.add(tag);
  }

  public boolean removeTag(NamedFacebookType tag) {
    return tags.remove(tag);
  }
}